package com.gildata.quote.client;

import static com.gildata.quote.client.MarketType.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gildata.quote.model.Exchange;
import com.gildata.quote.model.InitData;
import com.gildata.quote.model.Ticker;

@Service
public class QuoteManager {

	private static final Logger logger = LoggerFactory
			.getLogger(QuoteManager.class);

	@Autowired
	private QuoteClientHandler quoteClientHandler;

	private Set<CodeInfo> codes = new HashSet<CodeInfo>();

	private List<Ticker> tickers = new ArrayList<Ticker>();

	private Map<String, StockInitInfo> stockMap = new HashMap<String, StockInitInfo>();

	private Map<String, Exchange> exchangeMap = new HashMap<String, Exchange>();

	public QuoteManager() {
		super();
	}

	public void initAll(AnsInitialData msg) {
		// codes.clear();
		tickers.clear();
		stockMap.clear();
		exchangeMap.clear();
		for (OneMarketData marketData : msg.getMarketDatas()) {
			initMarket(marketData);
		}
	}

	public void initMarket(OneMarketData marketData) {
		initTimes(marketData);
		initTickers(marketData);
	}

	public Exchange getExchange(short market) {

		String code = QuoteUtils.toMarketCode(market);

		if (StringUtils.isEmpty(code)) {
			return null;
		} else {
			Exchange ex = exchangeMap.get(market);
			if (ex == null) {
				ex = new Exchange(code);
				exchangeMap.put(code, ex);
			}

			return ex;

		}
	}

	public void initTimes(OneMarketData marketData) {
		short marketType = marketData.getBourseInfo().getMarketType();
		int date = marketData.getBourseInfo().getDate();
		List<Integer> times = null;

		Exchange exchange = getExchange(marketType);

		if (exchange != null) {
			exchange.setDate(date);
			times = exchange.getTimes();
		}

		if (times != null) {
			times.clear();
			StockType st = marketData.getBourseInfo().getNewTypes().get(0);

			int i = 0;

			for (Time t : st.getTimes()) {

				int openTime = t.getOpenTime();
				int closeTime = t.getCloseTime();

				if (openTime == -1) {
					break;
				}

				if (i > 0) {
					openTime++;
				}
				while (openTime <= closeTime) {
					times.add(openTime);
					openTime++;
				}
				i++;

			}
		}

	}

	public void initTickers(OneMarketData marketData) {

		short marketType = marketData.getBourseInfo().getMarketType();

		if (isMarket(marketType, STOCK_MARKET)
				&& (isKind(marketType, KIND_INDEX)
						|| isKind(marketType, KIND_STOCKA) || isKind(
							marketType, KIND_STOCKB))) {
			for (StockInitInfo stock : marketData.getStockInitInfos()) {

				Ticker t = new Ticker(stock);
				tickers.add(t);
				stockMap.put(t.getSymbol(), stock);
			}
		}

		//
		// List<Ticker> tks = null;
		//
		// if (isMarketBourse(marketType, STOCK_MARKET, SH_BOURSE)) {
		// tks = getTickers();
		// } else if (isMarketBourse(marketType, STOCK_MARKET, SZ_BOURSE)) {
		// tks = getTickers();
		// } else if (isMarketBourse(marketType, FUTURES_MARKET, DALIAN_BOURSE))
		// {
		// // tks = getTickers();
		// } else if (isMarketBourse(marketType, FUTURES_MARKET,
		// SHANGHAI_BOURSE)) {
		// // tks = getTickers();
		// } else if (isMarketBourse(marketType, FUTURES_MARKET,
		// ZHENGZHOU_BOURSE)) {
		// // tks = getTickers();
		// } else if (isMarketBourse(marketType, FUTURES_MARKET,
		// HUANGJIN_BOURSE)) {
		// // tks = getTickers();
		// } else if (isMarketBourse(marketType, FUTURES_MARKET, GUZHI_BOURSE))
		// {
		// // tks = getTickers();
		// } else if (isMarketBourse(marketType, HK_MARKET, HK_BOURSE)) {
		// // tks = getTickers();
		// } else if (isMarketBourse(marketType, HK_MARKET, GE_BOURSE)) {
		// // tks = getTickers();
		// } else if (isMarketBourse(marketType, HK_MARKET, INDEX_BOURSE)) {
		// // tks = getTickers();
		// } else {
		//
		// }

	}

	public InitData getInitData() {
		return new InitData(tickers, exchangeMap);

	}

	public StockInitInfo findStockInitInfo(String symbol) {
		return stockMap.get(symbol);
	}

	public void subscribe(String symbol) {
		StockInitInfo stock = findStockInitInfo(symbol);
		logger.debug("stock: {}", stock);

		if (stock != null) {
			CodeInfo code = stock.getStockCode();
			codes.add(code);
			quoteClientHandler.reqAutoPush(codes);
		}

	}

	public void trend(String symbol) {
		StockInitInfo stock = findStockInitInfo(symbol);
		if (stock != null) {
			CodeInfo code = stock.getStockCode();
			quoteClientHandler.reqTrend(code);
		}
	}

	public void kline(String symbol, PeriodType period, int day) {
		StockInitInfo stock = findStockInitInfo(symbol);
		if (stock != null) {
			CodeInfo code = stock.getStockCode();
			quoteClientHandler.reqDayData(code, period, day);
		}
	}
	
	public void tick(String symbol, int count) {
		StockInitInfo stock = findStockInitInfo(symbol);
		if (stock != null) {
			CodeInfo code = stock.getStockCode();
			quoteClientHandler.reqLimitTick(code, count);
		}
	}

}
