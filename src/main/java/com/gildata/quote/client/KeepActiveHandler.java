package com.gildata.quote.client;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import org.springframework.stereotype.Component;


@Component
public class KeepActiveHandler extends ChannelDuplexHandler {

	private static final InternalLogger logger = InternalLoggerFactory.getInstance(KeepActiveHandler.class);  
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		
		logger.debug("userEventTriggered: {}", evt);
		
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                //ctx.close();
            } else if (e.state() == IdleState.WRITER_IDLE) {
                ctx.writeAndFlush(new ReqKeepActive());
            }
        }
		super.userEventTriggered(ctx, evt);
	}
	
	

}
