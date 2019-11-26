package com.north.lat.proxylat.agent;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 * agent,  使用javassist框架修改字节码, 然后使用Instrumentation替换字节码
 * @author lhh
 */
public class PrintTimeCostAgent {

    public static void premain(String agentArgs, Instrumentation inst){
        agentmain(agentArgs, inst);
    }

    public static void agentmain(String agentArgs, Instrumentation inst){
        System.out.println("agentArgs = [" + agentArgs + "], inst = [" + inst + "]");
        // 要修改的类, 例如com.north.lat.proxylat.service.impl.SimpleSearch
        String className = agentArgs;
        Class clazz;
        try {
            // 先要加载一下看这个类是否存在, 如果类本身不存在, 那自然也无法修改了
            clazz = Thread.currentThread().getContextClassLoader().loadClass(className);

            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get(className);
            // 使用javassist修改字节码
            byte[] classBytes = getRedefineClass(cc);
            if(classBytes == null){
                throw new IllegalStateException("getRedefineClass return null");
            }
            redefineClass(inst, clazz, classBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(" agentmain end");
    }
    private static void redefineClass(Instrumentation inst, Class clazz, byte [] classBytes) throws UnmodifiableClassException, ClassNotFoundException {
        // 使用Instrumentation替换JVM中的字节码
        ClassDefinition classDefinition = new ClassDefinition(clazz, classBytes);
        inst.redefineClasses(classDefinition);
    }

    private static byte[] getRedefineClass(CtClass cc){
        try {
            CtMethod[]  ctMethods = cc.getDeclaredMethods();
            // 在这个类的每个方法上都做AOP, 打印出执行时间
            for(CtMethod ctMethod : ctMethods){
                ctMethod.addLocalVariable("bt", CtClass.longType);
                ctMethod.addLocalVariable("et", CtClass.longType);
                ctMethod.insertBefore("bt = System.currentTimeMillis();");
                ctMethod.insertAfter(" et = System.currentTimeMillis();");
                ctMethod.insertAfter("System.out.println(\"javassist." + ctMethod.getName() + " cost : \" + (et - bt));");
            }
            return cc.toBytecode();
        } catch (CannotCompileException | IOException e) {
            e.printStackTrace();
        }
        // 异常情况返回null
        return null;
    }

}
