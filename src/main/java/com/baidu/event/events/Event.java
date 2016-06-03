package com.baidu.event.events;

import java.util.Map;

/**
 * 顶层event
 * @author hanjie.l
 *
 */
public interface Event {

	/**
	 * 获取事件的上下文对象
	 * @return 
	 */
	Map<String, Object> getContext();
	
	/**
	 * 获取标识key，一般为userId
	 * @return
	 */
	Object getKey();
	
	/**
	 * 获取事件名
	 * @return
	 */
	String getEventName();
	
}
