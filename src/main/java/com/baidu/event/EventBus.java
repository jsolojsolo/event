package com.baidu.event;

import com.baidu.event.events.Event;

/**
 * 事件总线接口
 * @author hanjie.l
 *
 */
public interface EventBus{

	/**
	 * 注册接收器
	 * @param receiver
	 */
	void register(Receiver<?> receiver);
	
	/**
	 * 注销接收器
	 * @param receiver
	 */
	void unregister(Receiver<?> receiver);
	
	/**
	 * 向事件总线提交事件
	 * @param event
	 */
	void post(Event event);
	
}