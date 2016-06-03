package com.baidu.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.event.events.Event;
import com.baidu.event.handler.EventHandler;

/**
 * 事件总线
 * 
 * @author hanjie.l
 * 
 */
@Component
public class EventBusImpl implements EventBus{
	
	/** 事件总线的执行器*/
	@Autowired
	private EventHandler eventHandler;

	/** 类 到 超类的缓存映射 */
	private static final Map<Class<?>, Set<Class<?>>> superClassCache = new HashMap<Class<?>, Set<Class<?>>>();
	
	/** 事件类型对应的接收器集合*/
	private final Map<Class<?>, Set<Receiver<?>>> receivers = new ConcurrentHashMap<Class<?>, Set<Receiver<?>>>();
	
	/**
	 * 获取事件类的超类集合(包括自己类)
	 * 
	 * @param eventType
	 * @return
	 */
	private Set<Class<?>> getAllSuperClasses(Class<?> eventType){
		Set<Class<?>> set = superClassCache.get(eventType);
		if(set == null){
			set =  findSuperClasses(eventType);
			superClassCache.put(eventType, set);
		}
		return set;
	}

	/**
	 * 找出所有父类，object除外
	 * 
	 * @param eventType
	 * @return
	 */
	private Set<Class<?>> findSuperClasses(Class<?> eventType) {
		Class<?> tempClass = eventType;
		Set<Class<?>> set = new HashSet<>();
		while (true) {
			if (tempClass == null || tempClass == Object.class) {
				break;
			}
			set.add(tempClass);
			Class<?>[] interfaces = tempClass.getInterfaces();
			for (Class<?> intf : interfaces) {
				set.add(intf);
			}

			tempClass = tempClass.getSuperclass();
		}
		return set;
	}
	
	/**
	 * 注册事件接收者
	 * @param receiver
	 */
	public void register(Receiver<?> receiver) {
		Class<?> eventType = resolveEventType(receiver);
		if(eventType == Object.class){
			throw new IllegalArgumentException(String.format("不支持 [%s] 类型的事件接收器 !", eventType));
		}
		Set<Receiver<?>> eventReceivers = receivers.get(eventType);
		if(eventReceivers == null){
			eventReceivers = new CopyOnWriteArraySet<Receiver<?>>();
			receivers.put(eventType, eventReceivers);
		}
		eventReceivers.add(receiver);
	}
	
	/**
	 * 注销
	 * @param receiver
	 */
	public void unregister(Receiver<?> receiver) {
		for(Set<Receiver<?>> receivers : this.receivers.values()){
			receivers.remove(receiver);
		}
	}
	
	/**
	 * 抛出事件
	 * @param event
	 */
	public void post(final Event event) {
		
		
		Set<Class<?>> types = getAllSuperClasses(event.getClass());
		boolean anyReceiver = false;
		
		for(Class<?> theType : types){
			Set<Receiver<?>> eventReceivers = receivers.get(theType);
			if(eventReceivers != null && !eventReceivers.isEmpty()){
				for(Receiver<?> receiver : eventReceivers){
					try {
						eventHandler.handle(event, receiver);
					} catch (Exception e) {
						System.out.println(String.format("提交事件 [%s] 异常!", event.getClass()));
						e.printStackTrace();
					}
					anyReceiver = true;
				}
			} 
		}
		
		if(!anyReceiver){
			System.out.println(String.format("事件 [%s] 没有接收器!", event.getClass()));
		}
	}
	
	
	
	
	/**
	 * 从接收器解析事件的类型
	 * @param receiver
	 * @return
	 */
	private Class<?> resolveEventType(Receiver<?> receiver){
		ParameterizedType type = (ParameterizedType)receiver.getClass().getGenericInterfaces()[0];
		Type eventType = type.getActualTypeArguments()[0];
		if(eventType instanceof Class){
			return (Class<?>)eventType;
		} else if(eventType instanceof ParameterizedType){
			return (Class<?>)((ParameterizedType)eventType).getRawType();
		} else {
			throw new IllegalArgumentException(String.format("不支持的事件类型 [%s]", eventType));
		}
	}
}
