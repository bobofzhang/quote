package com.gildata.quote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.PathVariable;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeEvent;
import org.springframework.stereotype.Controller;

import com.gildata.quote.client.PeriodType;
import com.gildata.quote.client.QuoteManager;
import com.gildata.quote.model.InitData;

@Controller
public class QuoteController {

	private static final Logger logger = LoggerFactory
			.getLogger(QuoteController.class);

	@Autowired
	private QuoteManager quoteManager;

	@SubscribeEvent("/init")
	public InitData init() throws Exception {
		return quoteManager.getInitData();
	}

	@MessageMapping("/subscribe/{symbol}")
	public void subscribe(@PathVariable String symbol) throws Exception {
		logger.debug("symbol: {}", symbol);
		quoteManager.subscribe(symbol);
	}

	@MessageMapping("/trend/{symbol}")
	public void trend(@PathVariable String symbol) throws Exception {
		logger.debug("symbol: {}", symbol);
		quoteManager.trend(symbol);
	}

	@MessageMapping("/kline/{symbol}")
	public void kline(@PathVariable String symbol) throws Exception {
		logger.debug("symbol: {}", symbol);
		quoteManager.kline(symbol, PeriodType.PERIOD_TYPE_DAY, 300);
	}

	@MessageMapping("/tick/{symbol}")
	public void tick(@PathVariable String symbol) throws Exception {
		logger.debug("symbol: {}", symbol);
		quoteManager.tick(symbol, 10);
	}

	@MessageExceptionHandler
	@SendToUser("/queue/errors/")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}

}
