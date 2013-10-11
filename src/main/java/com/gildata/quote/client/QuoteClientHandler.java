package com.gildata.quote.client;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import org.springframework.stereotype.Component;

/**
 * 
 * @author luhuiguo
 *
 */
@Component
@Sharable
public class QuoteClientHandler extends ChannelInboundHandlerAdapter {

	
	private static final InternalLogger logger = InternalLoggerFactory.getInstance(QuoteClientHandler.class);  
	
	private ChannelHandlerContext ctx;
	  
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		logger.debug("READ: {}", msg);
		
		if(msg instanceof AnsLogin){
			ReqInitSrv req= new ReqInitSrv();
			req.getServerCompares().add(new ServerCompare());
			ctx.writeAndFlush(req);
		}

		
		
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		login("guest","guest");
	}
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 */
	public void login(String username,String password){
		ctx.writeAndFlush(new ReqLogin(username,password));
	}
	
	
	

}
