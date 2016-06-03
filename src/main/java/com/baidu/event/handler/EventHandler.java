package com.baidu.event.handler;

import com.baidu.event.Receiver;
import com.baidu.event.events.Event;

/**
 * 事件处理器
 * @author hanjie.l
 *
 */
public interface EventHandler {

	/**
	 * 执行事件
	 * @param event
	 * @param receiver
	 */
	void handle(Event event, Receiver<?> receiver);
}