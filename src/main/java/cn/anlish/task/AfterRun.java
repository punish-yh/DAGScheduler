package cn.anlish.task;

/**
 * 将执行完毕的task注册到doneList等容器中
 *
 * @Author: Anlish
 * @Date: 2020/9/3
 */
public interface AfterRun {

    /**
     * finishedTask already finished task.
     * @param finishedTask
     * @return
     */
    public boolean run(Task<?> finishedTask);
}
