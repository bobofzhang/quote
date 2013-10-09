package com.gildata.quote.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;

import java.net.InetSocketAddress;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class QuoteClient {
	
	private static final Logger logger = LoggerFactory.getLogger(QuoteClient.class);
	
	@Autowired
	private Bootstrap bootstrap;
	
	@Autowired
	private InetSocketAddress address;
   
	private Channel channel;
	
	public void start() throws Exception {
		channel = bootstrap.connect(address).sync().channel();
	}

	public void stop() throws Exception {
		channel.closeFuture().sync();;
		
	}
	
	public void login() throws InterruptedException{
		logger.debug("channel:{}",channel);
		
		channel.writeAndFlush(new ReqLogin()).sync();
	}
    

}
