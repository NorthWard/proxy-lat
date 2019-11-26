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
                        // 先把当前的时间时间, 和方法名放到attach暂存着. 在after方法中再取出来
                        advice.attach(System.currentTimeMillis(), advice.getBehavior().getName());
                    }

                    @Override
                    protected void after(Advice advice) throws Throwable {
                        super.after(advice);
                        try{
                            String method = advice.getBehavior().getName();
                            // 如果这个method执行过before方法, 那么这里肯定为true
                            if(advice.hasMark(method)){
                                Object attachment = advice.attachment();
                                // attachment即为执行before方法的系统时间
                                Long bt = Long.parseLong(attachment.toString());
                                System.out.println(method +" cost: " + (System.currentTimeMillis()- bt));
                            }
                        }catch (Throwable e){
                             e.printStackTrace();
                             throw e;
                        }


                    }
                });

    }
}
