package com.baidu.event.handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.baidu.event.Receiver;
import com.baidu.event.events.Event;

/**
 * 异步事件处理器
 * @author hanjie.l
 *
 */
public class AsyncEventHandler implements EventHandler {
	

	private ExecutorService executor = Executors.newFixedThreadPool(5);
	
			
	@SuppressWarnings("rawtypes")
	@Override
	public void handle(final Event event, final Receiver receiver) {
		executor.execute(new Runnable() {
			
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
