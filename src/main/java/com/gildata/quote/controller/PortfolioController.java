/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gildata.quote.controller;

import java.security.Principal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeEvent;
import org.springframework.stereotype.Controller;

import com.gildata.quote.model.Portfolio;
import com.gildata.quote.model.PortfolioPosition;
import com.gildata.quote.service.PortfolioService;


@Controller
public class PortfolioController {

	private static final Log logger = LogFactory.getLog(PortfolioController.class);

	private final PortfolioService portfolioService;



	@Autowired
	public PortfolioController(PortfolioService portfolioService) {
		this.portfolioService = portfolioService;
	}

	@SubscribeEvent("/positions")
	public List<PortfolioPosition> getPortfolios(Principal principal) throws Exception {
		logger.debug("Positions for " + principal.getName());
		Portfolio portfolio = this.portfolioService.findPortfolio(principal.getName());
		return portfolio.getPositions();
	}


	@MessageExceptionHandler
	@SendToUser(value="/queue/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}

}
