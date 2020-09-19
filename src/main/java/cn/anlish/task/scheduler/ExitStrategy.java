package cn.anlish.task.scheduler;

/**
 * @Author: Anlish
 * @Date: 2020/9/3
 */
public interface ExitStrategy {
    /**
     * 等候所有scheduler调度完毕接口，超时时间设定单位为毫秒
     * @param time
     * @throws InterruptedException
     */
    void awaitAllSchedulerDone(long time) throws InterruptedException;
}
