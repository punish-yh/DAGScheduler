package cn.anlish.task.utils;

import cn.anlish.task.Constant;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @Author: Anlish
 * @Date: 2020/9/3
 */
public class TaskUtils {

    /**
     * 扫描给定目录下的所有job定义文件，不会递归扫描子文件夹
     * @param path
     * @return
     */
    public static File[] getAllJob(String path){
        File dir = new File(path);

        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.endsWith(Constant.INPUT_JSON_SUFFIX)){
                    return true;
                }
                return false;
            }
        });
    }
}
