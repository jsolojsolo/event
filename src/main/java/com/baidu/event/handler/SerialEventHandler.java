package com.baidu.event.handler;

import com.baidu.event.Receiver;
import com.baidu.event.events.Event;
import com.baidu.event.executor.SerialThreadExecutor;
/**
 * 异步有序事件处理器
 * @author hanjie.l
 *
 */
public class SerialEventHandler implements EventHandler{

	private SerialThreadExecutor executor = SerialThreadExecutor.getInstance();
	
	@SuppressWarnings("rawtypes")
	@Override
	public void handle(final Event event, final Receiver receiver) {
		executor.executeSerially(event.getKey(), new Runnable() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				try {
					receiver.onEvent(event);
				} catch (Exception e){
					System.out.println(String.format("执行事件 [%s] 异常!", event.getClass()));
					e.printStackTrace();
				}
			}
		});
	}
}
