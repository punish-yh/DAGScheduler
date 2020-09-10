package cn.anlish.task.meta;

import cn.anlish.task.Task;
import sun.swing.PrintingStatus;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Anlish
 * @Date: 2020/9/3
 */
public class TaskChains {
    private final List<Map<Task<?>, HashSet<Task<?>>>> chains;

    public TaskChains(){
        chains = new LinkedList<>();
    }

    public boolean addChain(Map<Task<?>, HashSet<Task<?>>> chain){
        return chains.add(chain);
    }

    public int getSize(){
        int size = 0;
        for(int i = 0; i < chains.size(); i++){
            size += chains.get(i).size();
        }
        return size;
    }

    /**
     * 删除空链
     */
    private void removeEmptyChain(){
        //TODO 这种删除有问题
        for(int i = 0; i < chains.size(); i++){
            if(chains.get(i).size() == 0){
                chains.remove(i);
            }
        }
    }

    public void removeTaskReference(Task<?> targetTask){
        removeEmptyChain();

        for(Map<Task<?>, HashSet<Task<?>>> allTask : chains){

        }
    }
}
