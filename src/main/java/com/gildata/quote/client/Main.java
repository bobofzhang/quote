package com.gildata.quote.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.gildata.quote.config.RootConfig;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		logger.info("test:{}",Integer.toHexString(EnvelopeType.valueOf("RT_COMPASKDATA").getValue()));

		@SuppressWarnings("resource")
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(
				RootConfig.class);
		ctx.registerShutdownHook();
	}
}
