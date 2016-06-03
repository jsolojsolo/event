package com.baidu.event.demo.task;

import com.baidu.event.events.demo.TaskEvent;
/**
 * 任务服务
 * @author hanjie.l
 *
 */
public interface TaskService {
	
	/**
	 * 做任务
	 */
	public void doTask(TaskEvent event);

}
