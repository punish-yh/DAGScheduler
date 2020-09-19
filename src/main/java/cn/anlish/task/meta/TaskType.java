package cn.anlish.task.meta;

/**
 * 记录用户自定义的作业类型，默认为NORMAL
 * 其中负数为调度器保留的作业类型，正数可为用户自定义
 * 用于区分标识task类型
 *
 * 这里不考虑使用enum是因为不方便让用户进行扩展
 *
 * @Author: Anlish
 * @Date: 2020/9/3
 */
public class TaskType {

    /**
     * 被标识为MOCONCURRENCY的task可以动态生成任务链加入父scheduler中执行
     */
    public static final TaskType MOCONCURRENCY = new TaskType("moconcurrency", -1);

    /**
     * 为了提高task的并发度，特设subtask类型（由moconcurrency动态返回）
     */
    public static final TaskType SUBTASK = new TaskType("subtask", -2);

    /**
     * 专用于监控其他taks执行结果的作业类型
     */
    public static final TaskType RESULT = new TaskType("result", -3);

    /**
     * 标志信号量，用于通知Dispatcher停止分发task
     */
    public static final TaskType ENDTASK = new TaskType("endtask", -4);

    /**
     * 标志该作业为模拟测试作业
     */
    public static final TaskType MOCKTASK = new TaskType("mocktask", -5);

    private final String name;

    private final int value;


    public TaskType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TaskType){
            TaskType oTaskType = (TaskType)obj;
            if(this.name.equals(oTaskType.name) && this.value == oTaskType.value){
                return true;
            }
        }
        return false;
    }
}
