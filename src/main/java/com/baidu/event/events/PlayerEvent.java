package com.baidu.event.events;

import java.util.HashMap;
import java.util.Map;

/**
 * 玩家事件抽象类
 * @author hanjie.l
 *
 */
public abstract class PlayerEvent implements Event{
	
	/**
	 * 玩家id
	 */
	private long playerId;
	
	/**
	 * 事件上下文
	 */
	protected Map<String, Object> eventContext = new HashMap<String, Object>();
	
	public PlayerEvent(Long playerId){
		this.playerId = playerId;
		this.eventContext.put("playerId", playerId);
	}

	@Override
	public Map<String, Object> getContext() {
		return eventContext;
	}

	@Override
	public Object getKey() {
		return playerId;
	}

}
