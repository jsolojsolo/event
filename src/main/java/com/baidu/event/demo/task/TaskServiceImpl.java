package com.baidu.event.demo.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.event.EventBus;
import com.baidu.event.Receiver;
import com.baidu.event.events.demo.TaskEvent;
@Component
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private EventBus eventBus;
	
	/**
	 * 初始化的时候注册事件接收者
	 */
	@PostConstruct
	public void init(){
		eventBus.register(new Receiver<TaskEvent>() {

			@Override
			public void onEvent(TaskEvent event) {
				
				doTask(event);
				
			}
		});
	}

	@Override
	public void doTask(TaskEvent event) {
		
		System.out.println(String.format("做与[%s]事件相关的任务", event.getEventName()));
		
	}
}
