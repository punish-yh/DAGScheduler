package cn.anlish.task.scheduler;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Anlish
 * @Date: 2020/9/3
 */
public class SchedulerPoolExitStrategy implements ExitStrategy {

    private final ThreadPoolExecutor schedulerPool;

    public SchedulerPoolExitStrategy(ThreadPoolExecutor schedulerPool){
        this.schedulerPool = schedulerPool;
    }

    @Override
    public void awaitAllSchedulerDone(long time) throws InterruptedException {
        while(schedulerPool.getQueue().size() != 0){
            Thread.sleep(500);
        }
        schedulerPool.shutdown();
        schedulerPool.awaitTermination(time, TimeUnit.MILLISECONDS);
    }
}
