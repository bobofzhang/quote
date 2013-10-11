package com.gildata.quote.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Slf4JLoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuoteClientInitializer extends ChannelInitializer<Channel> {
	
	@Autowired
	private LoggingHandler loggingHandler;
	
	@Autowired
	private EnvelopeEncoder encoder;
	
	@Autowired
	private EnvelopeDecoder decoder;
	
	@Autowired
	private QuoteClientHandler clientHandler;
	
	@Autowired
	private IdleStateHandler idleStateHandler;
	
	@Autowired
	private KeepActiveHandler keepActiveHandler;

	
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		InternalLoggerFactory.setDefaultFactory(new Slf4JLoggerFactory());
		pipeline.addLast("logger", loggingHandler);

		pipeline.addLast("decoder", decoder);
		pipeline.addLast("encoder", encoder);	
		pipeline.addLast("idleState", idleStateHandler);
		pipeline.addLast("keepActive", keepActiveHandler);
		pipeline.addLast("handler", clientHandler);
	}

}
