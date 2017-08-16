package net.fnsco.core.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
		* <p>Title: 多线程处理工作列表</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:02:52</p>
 */
public class TaskUtils {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 
    		*@name 异步执行任务
    		*@Description 相关说明 
    		*@Time 创建时间:2014年12月27日上午11:19:45
    		*@param runnable
     */
    public static void execAsyn(Runnable runnable) {
        executorService.execute(runnable);
    }
}
