package com.baidu.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baidu.event.demo.player.PlayerService;

public class Main {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		PlayerService bean = applicationContext.getBean(PlayerService.class);
		
		bean.dosomething1();
		
		bean.dosomething2();
	}

}
