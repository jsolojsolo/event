package com.baidu.event.demo.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.event.EventBus;
import com.baidu.event.events.demo.GoldEvent;
import com.baidu.event.events.demo.PlayerLevelUpEvent;

@Component
public class PlayerServiceImpl implements PlayerService{
	
	@Autowired
	private EventBus eventBus;

	@Override
	public void dosomething1() {

		//抛出升级事件
		eventBus.post(new PlayerLevelUpEvent(100001L, 10));
		
	}

	@Override
	public void dosomething2() {

		//抛出金币事件
		eventBus.post(new GoldEvent(100001L, 999));
		
	}

}
