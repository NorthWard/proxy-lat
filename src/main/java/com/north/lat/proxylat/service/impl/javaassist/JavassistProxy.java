package com.north.lat.proxylat.service.impl.javaassist;

import com.north.lat.proxylat.service.impl.SimpleSearch;
import javassist.*;

import java.io.IOException;

public class JavassistProxy extends SimpleSearch{
    private SimpleSearch simpleSearch;
    private ClassLoader classLoader;

    private static class JavassistProxyHolder{
        private static final JavassistProxy instance = new JavassistProxy();
    }
    private JavassistProxy(){
        try {
            this.classLoader = new CusClassLoader();
            // 通过单例模式来保证只编辑一次字节码
            this.simpleSearch = editSimpleSearchClass();
        } catch (NotFoundException | CannotCompileException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static JavassistProxy getInstance(){
        return  JavassistProxyHolder.instance;
    }

    @Override
    public String search(String key) {
        return simpleSearch.search(key);
    }

    @Override
    public String prettySearch(String key) {
        return simpleSearch.prettySearch(key);
    }

    private SimpleSearch editSimpleSearchClass() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.north.lat.proxylat.service.impl.SimpleSearch");
        CtClass newClass = pool.makeClass("com.north.lat.proxylat.service.impl.SimpleSearch$Proxy");
        newClass.setSuperclass(cc);
        CtMethod[]  ctMethods = cc.getDeclaredMethods();
        for(CtMethod ctMethod : ctMethods){
            ctMethod.addLocalVariable("bt", CtClass.longType);
            ctMethod.addLocalVariable("et", CtClass.longType);
            ctMethod.insertBefore("bt = System.currentTimeMillis();");
            ctMethod.insertAfter(" et = System.currentTimeMillis();");
            ctMethod.insertAfter("System.out.println(\"javassist." + ctMethod.getName() + " cost : \" + (et - bt));");
            newClass.addMethod(CtNewMethod.copy(ctMethod, newClass, null));
        }
        try {
            newClass.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (SimpleSearch) newClass.toClass().newInstance();

    }



    private static class CusClassLoader extends ClassLoader{
    }
}
