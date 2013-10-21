package com.gildata.quote.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gildata.quote.client.QuoteManager;
import com.gildata.quote.model.Instrument;

@Controller
@RequestMapping
public class QuoteController {
	
	@Autowired
	private QuoteManager quoteManager;

	private static final Logger logger = LoggerFactory
			.getLogger(QuoteController.class);

//	@RequestMapping(value = "/quote/{symbol:.+}")
//	public String quote(@PathVariable String symbol) {
//		logger.debug("symbol: {}", symbol);
//		
//		return "quote";
//
//	}
	
	
	@RequestMapping(value = "/instruments")
	@ResponseBody
	public List<Instrument> instruments() {
		return quoteManager.getInstruments();

	}
	
	
	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}
	

}
