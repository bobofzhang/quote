package com.gildata.quote.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Slf4JLoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuoteClient {

	private static final Logger logger = LoggerFactory
			.getLogger(QuoteClient.class);

	static final int RECONNECT_DELAY = 5;

	@Autowired
	private ServerList serverList;

	@Autowired
	private QuoteClientHandler clientHandler;


	final AtomicInteger count = new AtomicInteger(0);

	private Bootstrap configureBootstrap(Bootstrap b) {
		return configureBootstrap(b, new NioEventLoopGroup());
	}

	Bootstrap configureBootstrap(Bootstrap b, EventLoopGroup g) {
		b.group(g).channel(NioSocketChannel.class)
				.remoteAddress(serverList.getAddress())
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						InternalLoggerFactory
								.setDefaultFactory(new Slf4JLoggerFactory());
						pipeline.addLast("logger", new LoggingHandler(
								LogLevel.INFO));

						pipeline.addLast("decoder", new EnvelopeDecoder());
						pipeline.addLast("encoder", new EnvelopeEncoder());
						pipeline.addLast("idle", new IdleStateHandler(60, 30, 0));
						pipeline.addLast("handler", clientHandler);
					}

				});

		return b;
	}

	@PostConstruct
	public void run() {
		configureBootstrap(new Bootstrap()).connect().addListener(
				new ChannelFutureListener() {

					public void operationComplete(ChannelFuture future)
							throws Exception {
						logger.debug("operationComplete： {}", future);

						if (!future.isSuccess()) {
							logger.debug("failed");
							if (count.incrementAndGet() > serverList
									.getAddresses().size()) {
								
								logger.debug("serverList.getAddresses().size()={}",serverList
										.getAddresses().size());
							} else {
								// retry
								logger.debug("retrying");
								configureBootstrap(new Bootstrap()).connect()
										.addListener(this);
							}
						}
					}
				});

	}

}
