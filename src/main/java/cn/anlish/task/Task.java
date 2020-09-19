package cn.anlish.task;

import cn.anlish.task.meta.TaskState;
import cn.anlish.task.meta.TaskType;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * 用户通过编写run方法实现具体业务逻辑
 * Callback回调函数主要用来将任务注册到doneList中
 *
 * @Author: Anlish
 * @Date: 2020/9/3
 */
public abstract class Task<R> implements Callable<Task<R>> {

    //未导入log4j包
    //protected final Logger log;

    /**
     * 当前task归属的调度器id
     */
    private final String schedulerId;

    private final TaskType taskType;

    private final int taskId;

    private final AfterRun afterRun;

    private TaskState taskState;

    private boolean isAfterRunSuccess = false;

    public Task(String schedulerId, int taskId, TaskType taskType, AfterRun afterRun, Logger log) {
        this.schedulerId = schedulerId;
        this.taskType = taskType;
        this.taskId = taskId;
        this.afterRun = afterRun;
        //this.log = log;
        this.taskState = TaskState.PREPARE;
    }

    /**
     *run方法内需要将结果写入到resultChannel中
     * 尽量不要抛出异常，因为外层是线程池，没有人会处理这个异常
     * @throws Exception
     */
    public abstract void run() throws Exception;

    /**
     * 返回一个task，这个task可以动态决定当前作业有多少个subTask
     * 如果返回null则代表当前task不支持moreConcurrency功能
     * @return
     */
    public MoreConcurrencyTask getMoreConcurrency(){
        return null;
    }

    /**
     * 设定当前task依赖的前置task
     * @param t
     */
    public abstract void dependsOn(Task<?> t);

    public void dependsOn(Collection<Task<?>> tasks){
        for(Task<?> task : tasks){
            this.dependsOn(task);
        }
    }

    @Override
    public Task<R> call() throws Exception {
        try{
            taskState = TaskState.RUNNING;
            run();
            taskState = TaskState.SUCCESS;

        }catch (Exception e){
            taskState = TaskState.FAILED;
            //log.error(e.getMessage(), e);

        }finally {
            this.isAfterRunSuccess = afterRun.run(this);
        }
        return this;
    }

    /**
     * 设定作业状态为被移除
     */
    public void setRemoved(){
        this.taskState = TaskState.REMOVED;
    }

    public String getSchedulerId() {
        return schedulerId;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public int getTaskId() {
        return taskId;
    }

    public AfterRun getAfterRun() {
        return afterRun;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public boolean isAfterRunSuccess() {
        return isAfterRunSuccess;
    }

    @Override
    public String toString() {
        return "["
                + "schedulerId:" + schedulerId
                + "TaskType:" + taskType.getName()
                + "taskId:" + taskId
                + "TaskState:" + taskState.getName()
                + "]";
    }
}
