package com.gildata.quote.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Slf4JLoggerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuoteClientInitializer extends ChannelInitializer<SocketChannel> {
	
	private static final Logger logger = LoggerFactory.getLogger(QuoteClientInitializer.class);

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		InternalLoggerFactory.setDefaultFactory(new Slf4JLoggerFactory());
		pipeline.addLast("logger", new LoggingHandler(LogLevel.INFO));
		pipeline.addLast("encoder", new EnvelopeEncoder());
		pipeline.addLast("decoder", new LengthFieldBasedFrameDecoder(1048576,
				4, 4));
		pipeline.addLast("handler", new QuoteClientHandler());
	}

}
