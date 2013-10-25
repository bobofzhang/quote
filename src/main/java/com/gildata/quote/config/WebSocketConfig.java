package com.gildata.quote.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.EnableWebSocketMessageBroker;
import org.springframework.messaging.simp.config.MessageBrokerConfigurer;
import org.springframework.messaging.simp.config.StompEndpointRegistry;
import org.springframework.messaging.simp.config.WebSocketMessageBrokerConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer, SchedulingConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/quote").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerConfigurer configurer) {
		configurer.enableSimpleBroker("/queue/", "/topic/");
//		configurer.enableStompBrokerRelay("/queue/", "/topic/");
		configurer.setAnnotationMethodDestinationPrefixes("/app");
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskScheduler());
		
	}
	
	@Bean(destroyMethod="shutdown")
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(100);
    }

}

