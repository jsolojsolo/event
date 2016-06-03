package com.baidu.event;
/**
 * 事件接收器
 * @author hanjie.l
 *
 */
public interface Receiver<T> {

	/**
	 * 处理事件
	 * @param event
	 */
	void onEvent(T event);
	
}
