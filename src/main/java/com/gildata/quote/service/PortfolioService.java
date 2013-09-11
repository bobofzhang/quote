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
package com.gildata.quote.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gildata.quote.model.Portfolio;
import com.gildata.quote.model.PortfolioPosition;


/**
 * @author Rob Winch
 */
@Service
public class PortfolioService {

	// user -> Portfolio
	private final Map<String, Portfolio> portfolioLookup = new HashMap<>();


	public PortfolioService() {

		Portfolio portfolio = new Portfolio();
		portfolio.addPosition(new PortfolioPosition("上证指数", "1A0001", 2071.88, 0));
		portfolio.addPosition(new PortfolioPosition("深成指数", "399001", 18258.13, 0));
		portfolio.addPosition(new PortfolioPosition("恒生电子", "600570", 16.98, 0));
		portfolio.addPosition(new PortfolioPosition("平安银行", "000001", 31.22, 0));
		this.portfolioLookup.put("admin", portfolio);

		portfolio = new Portfolio();
		portfolio.addPosition(new PortfolioPosition("上证指数", "1A0001", 2071.88, 0));
		portfolio.addPosition(new PortfolioPosition("深成指数", "399001", 18258.13, 0));
		portfolio.addPosition(new PortfolioPosition("恒生电子", "600570", 16.98, 0));
		this.portfolioLookup.put("test", portfolio);
	}


	public Portfolio findPortfolio(String username) {
		Portfolio portfolio = this.portfolioLookup.get(username);
		if (portfolio == null) {
			throw new IllegalArgumentException(username);
		}
		return portfolio;
	}

}
