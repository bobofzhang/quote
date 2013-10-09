package com.gildata.quote.client;

import java.nio.ByteOrder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Slf4JLoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuoteClientInitializer extends ChannelInitializer<SocketChannel> {
	
	@Autowired
	private LoggingHandler loggingHandler;
	
	@Autowired
	private EnvelopeEncoder envelopeEncoder;
	
	@Autowired
	private EnvelopeDecoder envelopeDecoder;

	
	@Autowired
	private QuoteClientHandler quoteClientHandler;

	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		InternalLoggerFactory.setDefaultFactory(new Slf4JLoggerFactory());
		pipeline.addLast("logger", loggingHandler);
		pipeline.addLast("decoder", envelopeDecoder);
		pipeline.addLast("encoder", envelopeEncoder);		
		pipeline.addLast("handler", quoteClientHandler);
	}

}
