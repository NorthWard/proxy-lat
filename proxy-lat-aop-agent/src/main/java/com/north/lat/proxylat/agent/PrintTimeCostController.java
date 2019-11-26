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
    private static final String PID = "4696";
    /**
     * agent 所在的路径
     */
    private static final String AGENT_PATH = "F:\\mycode\\proxy-lat\\proxy-lat-aop-agent\\target\\proxy-lat-aop-agent-1.0-SNAPSHOT.jar";

    /**
     * 要增强的类的全限定名称
     */
    private static final String CLASS_NAME = "com.north.lat.proxylat.service.impl.SimpleSearch";

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        VirtualMachine virtualMachine = null;
        for(VirtualMachineDescriptor virtualMachineDescriptor : list){
            if(PID.equals(virtualMachineDescriptor.id())){
                virtualMachine = VirtualMachine.attach(virtualMachineDescriptor);
                virtualMachine.loadAgent(AGENT_PATH, CLASS_NAME);
            }
        }
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(virtualMachine != null){
        }
    }
}
