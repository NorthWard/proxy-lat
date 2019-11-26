package com.north.lat.proxylat.agent;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

/**
 * @author lhh
 */
public class PrintTimeCostController {
    /**
     * 目标JVM的pid
     */
    private static final String PID = "53569";
    /**
     * agent 所在的路径
     */
    private static final String AGENT_PATH = "/data/code/proxylat/proxy-lat-aop-agent/target/proxy-lat-aop-agent-1.0-SNAPSHOT.jar";

    /**
     * 要增强的类的全限定名称
     */
    private static final String CLASS_NAME = "com.north.lat.proxylat.service.impl.SimpleSearch";

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        VirtualMachine virtualMachine = null;
        for(VirtualMachineDescriptor virtualMachineDescriptor : list){
            // 如果是我们的目标PID, 才执行attach
            if(PID.equals(virtualMachineDescriptor.id())){
                virtualMachine = VirtualMachine.attach(virtualMachineDescriptor);
                // attach成功后, 执行loadAgent方法
                virtualMachine.loadAgent(AGENT_PATH, CLASS_NAME);
            }
        }
    }
}
