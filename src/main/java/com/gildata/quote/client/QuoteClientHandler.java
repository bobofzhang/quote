package com.gildata.quote.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class QuoteClientHandler extends SimpleChannelInboundHandler<Object> {

	
	private static final InternalLogger logger = InternalLoggerFactory.getInstance(QuoteClientHandler.class);  
	  
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg)
			throws Exception {

		logger.debug("msg:{}",msg);
		
	}

}
