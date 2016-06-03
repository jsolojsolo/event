package com.baidu.event.handler;

import com.baidu.event.Receiver;
import com.baidu.event.events.Event;

/**
 * 同步事件处理器
 * @author hanjie.l
 *
 */
public class SyncEventHandler implements EventHandler {
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void handle(final Event event, final Receiver receiver) {
		try {
			receiver.onEvent(event);
		} catch (Exception e){
			System.out.println(String.format("执行事件 [%s] 异常!", event.getClass()));
			e.printStackTrace();
		}
	}
}
