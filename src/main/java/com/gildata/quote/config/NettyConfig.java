package com.gildata.quote.config;

import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.gildata.quote.client.ServerList;

@Configuration
@ComponentScan(basePackages = { "com.gildata.quote.client" })
@PropertySource("classpath:netty.properties")
public class NettyConfig {

	@Bean
	public ServerList serverList(@Value("${servers}") String servers) {
		return new ServerList(servers);
	}

//	@Bean(name = "loggingHandler")
//	public LoggingHandler loggingHandler() {
//		return new LoggingHandler(LogLevel.INFO);
//	}
//
//	@Bean(name = "idleStateHandler")
//	public IdleStateHandler idleStateHandler() {
//		return new IdleStateHandler(60, 30, 0);
//	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
