/*
package jvmsandbox;

import com.alibaba.jvm.sandbox.api.Information;
import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.api.annotation.Command;
import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
import org.kohsuke.MetaInfServices;

import javax.annotation.Resource;


@MetaInfServices(Module.class)
@Information(id = "print-cost-time")
public class PrintCostModule implements Module {
    @Resource
    private ModuleEventWatcher moduleEventWatcher;

    @Command("printCostTime")
    public void printCostTime() {

        new EventWatchBuilder(moduleEventWatcher)
                .onClass("com.north.lat.proxylat.service.impl.SimpleSearch")
                .onBehavior("prettySearch")
                .onBehavior("search")
                .onWatch(new AdviceListener() {

                    @Override
                    protected void before(Advice advice) throws Throwable {
                        super.before(advice);
                        advice.attach(System.currentTimeMillis(), advice.getBehavior().getName());
                    }

                    @Override
                    protected void after(Advice advice) throws Throwable {
                        super.after(advice);
                        try{
                            String name = advice.getBehavior().getName();
                            if(advice.hasMark(name)){
                                Object attachment = advice.attachment();
                                Long bt = Long.parseLong(attachment.toString());
                                System.out.println(name +" cost: " + (System.currentTimeMillis()- bt));
                            }
                        }catch (Throwable e){
                             e.printStackTrace();
                             throw e;
                        }


                    }
                });

    }
}
*/
