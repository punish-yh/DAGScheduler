import cn.anlish.task.utils.TaskUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @Author: Anlish
 * @Date: 2020/9/9
 */
public class TaskUtilsTest {


    /**
     * 测试输入不存在路径时是否返回空
     *
     */
    @Test
    public void getAllJobTest1(){
        File[] files = TaskUtils.getAllJob("C:/wqasdfad/wFASDFAWE/ASFDad/afsdewf");
        Assertions.assertEquals(files.length, 0);
    }

    /**
     * 测试输入路径为文件路径时是否成功返回
     *
     */
    @Test
    public void getAllJobTest2(){

    }
}
