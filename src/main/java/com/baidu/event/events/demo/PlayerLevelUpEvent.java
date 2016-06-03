package com.baidu.event.events.demo;

import com.baidu.event.events.PlayerEvent;

/**
 * 玩家升级事件
 * @author hanjie.l
 *
 */
public class PlayerLevelUpEvent extends PlayerEvent implements TaskEvent{

	/**
	 * 构造函数
	 * @param playerId 玩家id
	 * @param level 升级后等级
	 */
	public PlayerLevelUpEvent(Long playerId, int level) {
		super(playerId);
		getContext().put("level", level);
	}

	@Override
	public String getEventName() {
		return "PlayerLevelUpEvent";
	}

}
