package com.gildata.quote.client;

import static com.gildata.quote.client.MarketType.FUTURES_MARKET;
import static com.gildata.quote.client.MarketType.HK_MARKET;
import static com.gildata.quote.client.MarketType.STOCK_MARKET;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.net.ConnectException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.BrokerAvailabilityEvent;
import org.springframework.stereotype.Component;

/**
 * 
 * @author luhuiguo
 * 
 */
@Component
@Sharable
public class QuoteClientHandler extends ChannelInboundHandlerAdapter implements
		ApplicationListener<BrokerAvailabilityEvent> {

	private static final InternalLogger logger = InternalLoggerFactory
			.getInstance(QuoteClientHandler.class);

	private ChannelHandlerContext ctx;

	private AtomicBoolean brokerAvailable = new AtomicBoolean();

	@Autowired
	private QuoteClient quoteClient;

	@Autowired
	private QuoteManager quoteManager;

	@Autowired
	private MessageSendingOperations<String> messagingTemplate;

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		logger.info("Connected to: {}", ctx.channel().remoteAddress());
		reqLogin("guest", "guest");
	}

	@Override
	public void channelInactive(final ChannelHandlerContext ctx)
			throws Exception {
		super.channelInactive(ctx);
		logger.info("Disconnected from: {}", ctx.channel().remoteAddress());

		logger.info("Sleeping for: {} s", QuoteClient.RECONNECT_DELAY);

		final EventLoop loop = ctx.channel().eventLoop();
		loop.schedule(new Runnable() {
			@Override
			public void run() {
				logger.info("Reconnecting...");
				quoteClient.configureBootstrap(new Bootstrap(), loop).connect();
			}
		}, QuoteClient.RECONNECT_DELAY, TimeUnit.SECONDS);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		logger.debug("userEventTriggered: {}", evt);

		if (evt instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) evt;
			if (e.state() == IdleState.READER_IDLE) {
				// ctx.close();
			} else if (e.state() == IdleState.WRITER_IDLE) {
				ctx.writeAndFlush(new ReqKeepActive());
			}
		}
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		if (cause instanceof ConnectException) {
			logger.warn("Failed to connect: ", cause);
		}
		ctx.close();
		super.exceptionCaught(ctx, cause);
	}

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
		} else if (msg instanceof AnsDayData) {
			ansDayData((AnsDayData) msg);
		} else if (msg instanceof AnsDayDataEx) {
			ansDayDataEx((AnsDayDataEx) msg);
		}

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

	public void reqRealTime(Collection<CodeInfo> codes) {
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

	public void reqDayData(CodeInfo code, PeriodType period, int day) {
		ReqDayData ask = new ReqDayData();
		ask.setCodeInfo(code);
		ask.setPeriod(period);
		ask.setDay((short) day);
		ask.getPrivateKey().setCodeInfo(code);
		ctx.writeAndFlush(ask);
	}

	public void reqLimitTick(CodeInfo code, int count) {
		ReqLimitTick req = new ReqLimitTick(code, (short) count);
		req.getPrivateKey().setCodeInfo(code);
		ctx.writeAndFlush(req);
	}

	public void reqAutoPush(Collection<CodeInfo> codes) {
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
		quoteManager.initAll(msg);

	}

	public void ansServerInfo(AnsServerInfo msg) {

	}

	public void ansRealTime(AnsRealTime msg) {
		for (RealTimeData data : msg.getDatas()) {
			sendQuote(data);
		}
	}

	public void ansAutoPush(AnsAutoPush msg) {
		for (RealTimeData data : msg.getDatas()) {
			sendQuote(data);
		}
	}

	public void sendQuote(RealTimeData data) {
		if (this.brokerAvailable.get()) {
			this.messagingTemplate.convertAndSend("/topic/quote/"
					+ data.getCodeInfo().toSymbol(), data);
		}

	}

	public void ansTrendData(AnsTrendData msg) {
		if (this.brokerAvailable.get()) {
			this.messagingTemplate.convertAndSend("/queue/trend/"
					+ msg.getPrivateKey().getCodeInfo().toSymbol(), msg);
		}
	}

	public void ansKeepActive(AnsKeepActive msg) {

	}

	public void ansStockTick(AnsStockTick msg) {
		if (this.brokerAvailable.get()) {
			this.messagingTemplate.convertAndSend("/queue/tick/"
					+ msg.getPrivateKey().getCodeInfo().toSymbol(), msg);
		}
	}

	public void ansTick(AnsTick msg) {
		if (this.brokerAvailable.get()) {
			this.messagingTemplate.convertAndSend("/queue/tick/"
					+ msg.getPrivateKey().getCodeInfo().toSymbol(), msg);
		}
	}

	public void ansDayData(AnsDayData msg) {
		if (this.brokerAvailable.get()) {
			this.messagingTemplate.convertAndSend("/queue/kline/"
					+ msg.getPrivateKey().getCodeInfo().toSymbol(),
					msg.getDatas());
		}
	}

	public void ansDayDataEx(AnsDayDataEx msg) {
		if (this.brokerAvailable.get()) {
			this.messagingTemplate.convertAndSend("/queue/kline/"
					+ msg.getPrivateKey().getCodeInfo().toSymbol(),
					msg.getDatas());
		}
	}

	@Override
	public void onApplicationEvent(BrokerAvailabilityEvent event) {
		this.brokerAvailable.set(event.isBrokerAvailable());
	}

}
