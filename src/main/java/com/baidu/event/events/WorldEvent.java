package com.baidu.event.events;

import java.util.HashMap;
import java.util.Map;

/**
 * 世界事件抽象类
 * @author hanjie.l
 *
 */
public abstract class WorldEvent implements Event{
	
	/**
	 * 事件上下文
	 */
	protected Map<String, Object> eventContext = new HashMap<String, Object>();
	
	@Override
	public Map<String, Object> getContext() {
		return eventContext;
	}

	@Override
	public Object getKey() {
		return "WORLD";
	}

}
