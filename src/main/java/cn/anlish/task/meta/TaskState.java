package cn.anlish.task.meta;

/**
 * 用于记录task生命周期中的状态
 *
 * @Author: Anlish
 * @Date: 2020/9/3
 */
public enum TaskState {
    /**
     * 初始状态
     * task从未执行过
     */
    PREPARE("prepare", 1),

    /**
     * 执行状态
     * task被调度并占用CPU后会进入该状态
     */
    RUNNING("running", 2),

    /**
     * 执行成功
     */
    SUCCESS("success", 3),

    /**
     * 重试状态
     * 如果设定了失败重试机制则作业失败后会先进行重试并进入RETRY状态
     */
    RETRY("retry", 4),

    /**
     * 执行失败
     * FAILED作业表示在执行过程中作业失败
     * 如果配置了作业重试次数的话会先进入RETRY
     * 失败次数超过重试次数后进入FAILED状态
     */
    FAILED("failed", 5),

    /**
     * 作业被移除
     * REMOVED状态和FAILED状态的区别在于
     * REMOVED作业是由于其依赖的作业执行失败导致后续作业无法执行从而被移除
     * 也就是被标记REMOVED的作业从未运行过
     *
     */
    REMOVED("removed", 6);

    private final String name;

    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    TaskState(String name, int value) {
        this.name = name;
        this.value = value;
    }

}
