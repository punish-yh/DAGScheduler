package cn.anlish.task.scheduler;

import cn.anlish.task.Scheduler;

/**
 * @Author: Anlish
 * @Date: 2020/9/3
 */
public class SchedulerExitStrategy implements ExitStrategy{
    private final Scheduler scheduler;

    public SchedulerExitStrategy(Scheduler scheduler){
        this.scheduler = scheduler;
    }


    @Override
    public void awaitAllSchedulerDone(long time) throws InterruptedException {
        while(!scheduler.isFinished()){
            Thread.sleep(time);
        }
    }
}
