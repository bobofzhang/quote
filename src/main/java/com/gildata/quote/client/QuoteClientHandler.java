package com.gildata.quote.client;

import static com.gildata.quote.client.MarketType.FUTURES_MARKET;
import static com.gildata.quote.client.MarketType.HK_MARKET;
import static com.gildata.quote.client.MarketType.KIND_STOCKA;
import static com.gildata.quote.client.MarketType.SH_BOURSE;
import static com.gildata.quote.client.MarketType.STOCK_MARKET;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 
 * @author luhuiguo
 * 
 */
@Component
@Sharable
public class QuoteClientHandler extends ChannelInboundHandlerAdapter {

	private static final InternalLogger logger = InternalLoggerFactory
			.getInstance(QuoteClientHandler.class);

	private ChannelHandlerContext ctx;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {

		logger.trace("READ: {}", msg);

		if (msg instanceof AnsLogin) {
			ansLogin((AnsLogin) msg);
		} else if (msg instanceof AnsInitialData) {
			ansInitialData((AnsInitialData) msg);
		} else if (msg instanceof AnsServerInfo) {
			ansServerInfo((AnsServerInfo) msg);
		} else if (msg instanceof AnsRealTime) {
			ansRealTime((AnsRealTime) msg);
		} else if (msg instanceof AnsAutoPush) {
			ansAutoPush((AnsAutoPush) msg);
		} else if (msg instanceof AnsTrendData) {
			ansTrendData((AnsTrendData) msg);
		} else if (msg instanceof AnsKeepActive) {
			ansKeepActive((AnsKeepActive) msg);
		} else if (msg instanceof AnsStockTick) {
			ansStockTick((AnsStockTick) msg);
		} else if (msg instanceof AnsTick) {
			ansTick((AnsTick) msg);
		}

	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		reqLogin("guest", "guest");
	}

	public void reqLogin(String username, String password) {
		ctx.writeAndFlush(new ReqLogin(username, password));
	}

	public void reqLoginHK(String username, String password) {
		ctx.writeAndFlush(new ReqLogin(EnvelopeType.RT_LOGIN_HK, username,
				password));
	}

	public void reqLoginFutures(String username, String password) {
		ctx.writeAndFlush(new ReqLogin(EnvelopeType.RT_LOGIN_FUTURES, username,
				password));
	}

	public void reqLoginForeign(String username, String password) {
		ctx.writeAndFlush(new ReqLogin(EnvelopeType.RT_LOGIN_FOREIGN, username,
				password));
	}

	public void reqLoginWP(String username, String password) {
		ctx.writeAndFlush(new ReqLogin(EnvelopeType.RT_LOGIN_WP, username,
				password));
	}

	public void reqLoginInfo(String username, String password) {
		ctx.writeAndFlush(new ReqLogin(EnvelopeType.RT_LOGIN_INFO, username,
				password));
	}

	public void reqLoginLevel(String username, String password) {
		ctx.writeAndFlush(new ReqLogin(EnvelopeType.RT_LOGIN_LEVEL, username,
				password));
	}

	public void reqInitSrv(ServerType serverType) {
		ReqInitSrv req = new ReqInitSrv();
		ServerCompare sc = new ServerCompare();
		switch (serverType) {
		case ST_STOCK_HK:
			sc.setBourse(HK_MARKET);
			break;
		case ST_FUTURES:
			sc.setBourse(FUTURES_MARKET);
			break;
		case ST_STOCK_LEVEL2:
			sc.setBourse(STOCK_MARKET);
			break;

		default:
			sc.setBourse(STOCK_MARKET);
			break;
		}

		req.getServerCompares().add(sc);
		ctx.writeAndFlush(req);

	}

	public void reqRealTime(List<CodeInfo> codes) {
		AskData ask = new AskData(EnvelopeType.RT_REALTIME, codes);
		ctx.writeAndFlush(ask);

	}

	public void reqTrend(CodeInfo code) {
		AskData ask = new AskData(EnvelopeType.RT_TREND,
				new CodeInfo[] { code });
		ask.getPrivateKey().setCodeInfo(code);
		ctx.writeAndFlush(ask);

	}

	public void reqTick(CodeInfo code) {
		AskData ask = new AskData(EnvelopeType.RT_TICK, new CodeInfo[] { code });
		ask.getPrivateKey().setCodeInfo(code);
		ctx.writeAndFlush(ask);

	}

	public void reqStockTick(CodeInfo code) {
		AskData ask = new AskData(EnvelopeType.RT_STOCKTICK,
				new CodeInfo[] { code });
		ask.getPrivateKey().setCodeInfo(code);
		ctx.writeAndFlush(ask);

	}

	public void reqLimitTick(CodeInfo code, int count) {
		ReqLimitTick req = new ReqLimitTick(code, (short) count);
		req.getPrivateKey().setCodeInfo(code);
		ctx.writeAndFlush(req);

	}

	public void reqAutoPush(List<CodeInfo> codes) {
		AskData ask = new AskData(EnvelopeType.RT_AUTOPUSH, codes);
		ctx.writeAndFlush(ask);

	}

	public void ansLogin(AnsLogin msg) {
		switch (msg.type) {
		case RT_LOGIN_HK:
			reqInitSrv(ServerType.ST_STOCK_HK);
			break;
		case RT_LOGIN_FUTURES:
			reqInitSrv(ServerType.ST_FUTURES);
			break;
		case RT_LOGIN_LEVEL:
			reqInitSrv(ServerType.ST_STOCK_LEVEL2);
			break;
		default:
			reqInitSrv(ServerType.ST_STOCK_LEVEL1);
			break;

		}

	}

	public void ansInitialData(AnsInitialData msg) {
		// logger.debug("{}", msg);

		List<CodeInfo> codes = new ArrayList<CodeInfo>();
		CodeInfo c600570 = new CodeInfo(
				(short) (STOCK_MARKET | SH_BOURSE | KIND_STOCKA), "600570");
		CodeInfo c600571 = new CodeInfo(
				(short) (STOCK_MARKET | SH_BOURSE | KIND_STOCKA), "600571");
		codes.add(c600570);
		codes.add(c600571);
		reqAutoPush(codes);
		reqTick(c600570);
	}

	public void ansServerInfo(AnsServerInfo msg) {
		logger.debug("{}", msg);
	}

	public void ansRealTime(AnsRealTime msg) {
		logger.debug("{}", msg);
	}

	public void ansAutoPush(AnsAutoPush msg) {
		logger.debug("{}", msg);
	}

	public void ansTrendData(AnsTrendData msg) {
		logger.debug("{}", msg);
	}

	public void ansKeepActive(AnsKeepActive msg) {
		logger.debug("{}", msg);
	}

	public void ansStockTick(AnsStockTick msg) {
		logger.debug("{}", msg);
	}

	public void ansTick(AnsTick msg) {
		logger.debug("{}", msg);
	}

}
