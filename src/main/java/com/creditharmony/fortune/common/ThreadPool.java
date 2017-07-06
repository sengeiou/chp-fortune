package com.creditharmony.fortune.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPool {
	/**
	 * 日志对象
	 */
	protected static Logger logger = LoggerFactory.getLogger(ThreadPool.class);
	// 单例
	private static ThreadPool threadPool =null;
	// 线程数量
	private static final int poolMax = 10;
	private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(poolMax);  
	private static ThreadPoolExecutor pool = new ThreadPoolExecutor(poolMax, poolMax, 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());
	
	
	private ThreadPool(){
		
	}
	
    /**
     * 初始化对象
     * 2015年12月26日
     * By 韩龙
     * @return
     */
    public static  ThreadPool getInstance(){
		if(threadPool != null ){
			return threadPool;
		}
		return new ThreadPool(); 
	}
	
    /**
     * 
     * 2015年12月26日
     * By 韩龙
     * @param httpDownload
     */
	public void addTask(FileManagement httpDownload){
		pool.execute(httpDownload);
	}
	
	/**
	 * 关闭线程
	 * 2015年12月26日
	 * By 韩龙
	 */
	public void stopTask(){
		pool.shutdown();
	}
	
}
