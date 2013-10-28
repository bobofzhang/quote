package com.gildata.quote.client;

import static com.gildata.quote.client.MarketType.DALIAN_BOURSE;
import static com.gildata.quote.client.MarketType.FUTURES_MARKET;
import static com.gildata.quote.client.MarketType.GE_BOURSE;
import static com.gildata.quote.client.MarketType.GUZHI_BOURSE;
import static com.gildata.quote.client.MarketType.HK_BOURSE;
import static com.gildata.quote.client.MarketType.HK_MARKET;
import static com.gildata.quote.client.MarketType.HUANGJIN_BOURSE;
import static com.gildata.quote.client.MarketType.INDEX_BOURSE;
import static com.gildata.quote.client.MarketType.SHANGHAI_BOURSE;
import static com.gildata.quote.client.MarketType.SH_BOURSE;
import static com.gildata.quote.client.MarketType.STOCK_MARKET;
import static com.gildata.quote.client.MarketType.SZ_BOURSE;
import static com.gildata.quote.client.MarketType.ZHENGZHOU_BOURSE;
import static com.gildata.quote.client.MarketType.isMarketBourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gildata.quote.model.Exchange;
import com.gildata.quote.model.Instrument;
import com.gildata.quote.model.Ticker;

@Service
public class QuoteManager {

	private static final Logger logger = LoggerFactory
			.getLogger(QuoteManager.class);

	@Autowired
	private QuoteClientHandler quoteClientHandler;

	private Set<CodeInfo> codes = new HashSet<CodeInfo>();

	private List<Ticker> tickers = new ArrayList<Ticker>();

	private Map<String, Ticker> tickerMap = new HashMap<String, Ticker>();

	public QuoteManager() {
		super();
	}

	public List<Ticker> getTickers() {
		if (tickers == null) {
			tickers = new ArrayList<Ticker>();
		}
		return tickers;
	}

	public void setTickers(List<Ticker> tickers) {
		this.tickers = tickers;
	}

	public Map<String, Ticker> getTickerMap() {
		if (tickerMap == null) {
			tickerMap = new HashMap<String, Ticker>();
		}
		return tickerMap;
	}

	public void setTickerMap(Map<String, Ticker> tickerMap) {
		this.tickerMap = tickerMap;
	}

	public void initAll(OneMarketData marketData) {
		initTimes(marketData);
		initTickers(marketData);
	}

	public void initTimes(OneMarketData marketData) {
		short marketType = marketData.getBourseInfo().getMarketType();
		int date = marketData.getBourseInfo().getDate();
		List<Integer> times = null;

		Exchange exchange = Exchange.getExchange(marketType);

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

		List<Ticker> tks = null;

		if (isMarketBourse(marketType, STOCK_MARKET, SH_BOURSE)) {
			tks = getTickers();
		} else if (isMarketBourse(marketType, STOCK_MARKET, SZ_BOURSE)) {
			tks = getTickers();
		} else if (isMarketBourse(marketType, FUTURES_MARKET, DALIAN_BOURSE)) {
			// tks = getTickers();
		} else if (isMarketBourse(marketType, FUTURES_MARKET, SHANGHAI_BOURSE)) {
			// tks = getTickers();
		} else if (isMarketBourse(marketType, FUTURES_MARKET, ZHENGZHOU_BOURSE)) {
			// tks = getTickers();
		} else if (isMarketBourse(marketType, FUTURES_MARKET, HUANGJIN_BOURSE)) {
			// tks = getTickers();
		} else if (isMarketBourse(marketType, FUTURES_MARKET, GUZHI_BOURSE)) {
			// tks = getTickers();
		} else if (isMarketBourse(marketType, HK_MARKET, HK_BOURSE)) {
			// tks = getTickers();
		} else if (isMarketBourse(marketType, HK_MARKET, GE_BOURSE)) {
			// tks = getTickers();
		} else if (isMarketBourse(marketType, HK_MARKET, INDEX_BOURSE)) {
			// tks = getTickers();
		} else {

		}

		if (tks != null) {
			for (StockInitInfo stock : marketData.getStockInitInfos()) {
				Ticker t = new Ticker(stock);
				tks.add(t);
				getTickerMap().put(t.getSymbol(), t);
			}
		}

	}

	public Ticker getTicker(String symbol) {
		return getTickerMap().get(symbol);
	}

	public Instrument info(String symbol) {
		Ticker ticker = getTicker(symbol);
		logger.debug("ticker: {}", ticker);

		if (ticker != null) {
			CodeInfo code = new CodeInfo(ticker.getMarket(), ticker.getCode());
			quoteClientHandler.reqTrend(code);
			quoteClientHandler.reqLimitTick(code, 10);
			codes.add(code);
			// quoteClientHandler.reqRealTime(codes);
			quoteClientHandler.reqAutoPush(codes);

			return new Instrument(ticker);
		} else {
			return null;
		}

	}

	public void trend(String symbol) {
		Ticker t = getTicker(symbol);
		if (t != null) {
			CodeInfo code = new CodeInfo(t.getMarket(), t.getCode());
			quoteClientHandler.reqTrend(code);
		}
	}

	public void kline(String symbol, PeriodType period, int day) {
		Ticker t = getTicker(symbol);
		if (t != null) {
			CodeInfo code = new CodeInfo(t.getMarket(), t.getCode());
			quoteClientHandler.reqDayData(code, period, day);
		}
	}

}
