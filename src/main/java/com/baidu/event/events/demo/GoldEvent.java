package com.baidu.event.events.demo;

import com.baidu.event.events.PlayerEvent;
/**
 * 获得金币事件
 * @author hanjie.l
 *
 */
public class GoldEvent extends PlayerEvent implements TaskEvent{
	/**
	 * 构造函数
	 * @param playerId 玩家id
	 * @param gold 获得多少金币
	 */
	public GoldEvent(Long playerId, int gold) {
		super(playerId);
		getContext().put("gold", gold);
	}

	@Override
	public String getEventName() {
		return "GoldEvent";
	}
}
