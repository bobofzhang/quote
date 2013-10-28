package com.gildata.quote.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.PathVariable;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gildata.quote.client.PeriodType;
import com.gildata.quote.client.QuoteManager;
import com.gildata.quote.model.Instrument;
import com.gildata.quote.model.Ticker;

@Controller
public class QuoteController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(QuoteController.class);
	
	@Autowired
	private QuoteManager quoteManager;
	
	@RequestMapping(value = "/tickers")
	@ResponseBody
	public List<Ticker> tickers() {
		return quoteManager.getTickers();

	}
	
	@SubscribeEvent("/info/{symbol}")
	public Instrument info(@PathVariable String symbol) throws Exception {
		logger.debug("symbol: {}", symbol);
		return quoteManager.info(symbol);
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
	
	
	@MessageExceptionHandler
	@SendToUser("/queue/errors/")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}
	

}
