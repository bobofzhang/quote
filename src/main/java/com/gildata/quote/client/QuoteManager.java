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

@Service
public class QuoteManager {

	private static final Logger logger = LoggerFactory
			.getLogger(QuoteManager.class);
	
	@Autowired
	private QuoteClientHandler quoteClientHandler;

	private Set<CodeInfo> codes = new HashSet<CodeInfo>();

	private List<Instrument> instruments = new ArrayList<Instrument>();

	private Map<String, Instrument> instrumentMap = new HashMap<String, Instrument>();

	public QuoteManager() {
		super();
	}

	public List<Instrument> getInstruments() {
		if (instruments == null) {
			instruments = new ArrayList<Instrument>();
		}
		return instruments;
	}

	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}

	public Map<String, Instrument> getInstrumentMap() {
		if (instrumentMap == null) {
			instrumentMap = new HashMap<String, Instrument>();
		}
		return instrumentMap;
	}

	public void setInstrumentMap(Map<String, Instrument> instrumentMap) {
		this.instrumentMap = instrumentMap;
	}

	public void initAll(OneMarketData marketData) {
		initTimes(marketData);
		initInstruments(marketData);
	}

	public void initTimes(OneMarketData marketData) {
		short marketType = marketData.getBourseInfo().getMarketType();
		int date = marketData.getBourseInfo().getDate();
		List<Integer> times = null;

		Exchange exchange = Exchange.getExchange(marketType);
		
		if (exchange != null){
			exchange.setDate(date);
			times = exchange.getTimes();
		}

//		if (isMarketBourse(marketType, STOCK_MARKET, SH_BOURSE)) {
//			Exchange.SH_STOCK_EXCHANGE.setDate(date);
//			times = Exchange.SH_STOCK_EXCHANGE.getTimes();
//
//		} else if (isMarketBourse(marketType, STOCK_MARKET, SZ_BOURSE)) {
//			Exchange.SZ_STOCK_EXCHANGE.setDate(date);
//			times = Exchange.SZ_STOCK_EXCHANGE.getTimes();
//
//		} else if (isMarketBourse(marketType, FUTURES_MARKET, DALIAN_BOURSE)) {
//			Exchange.DL_FUTURES_EXCHANGE.setDate(date);
//			times = Exchange.DL_FUTURES_EXCHANGE.getTimes();
//
//		} else if (isMarketBourse(marketType, FUTURES_MARKET, SHANGHAI_BOURSE)) {
//			Exchange.SH_FUTURES_EXCHANGE.setDate(date);
//			times = Exchange.SH_FUTURES_EXCHANGE.getTimes();
//
//		} else if (isMarketBourse(marketType, FUTURES_MARKET, ZHENGZHOU_BOURSE)) {
//			Exchange.ZZ_FUTURES_EXCHANGE.setDate(date);
//			times = Exchange.ZZ_FUTURES_EXCHANGE.getTimes();
//
//		} else if (isMarketBourse(marketType, FUTURES_MARKET, HUANGJIN_BOURSE)) {
//			Exchange.GD_FUTURES_EXCHANGE.setDate(date);
//			times = Exchange.GD_FUTURES_EXCHANGE.getTimes();
//
//		} else if (isMarketBourse(marketType, FUTURES_MARKET, GUZHI_BOURSE)) {
//			Exchange.IF_FUTURES_EXCHANGE.setDate(date);
//			times = Exchange.IF_FUTURES_EXCHANGE.getTimes();
//
//		} else if (isMarketBourse(marketType, HK_MARKET, HK_BOURSE)) {
//			Exchange.HK_STOCK_EXCHANGE.setDate(date);
//			times = Exchange.HK_STOCK_EXCHANGE.getTimes();
//
//		} else if (isMarketBourse(marketType, HK_MARKET, GE_BOURSE)) {
//
//		} else if (isMarketBourse(marketType, HK_MARKET, INDEX_BOURSE)) {
//
//		} else {
//
//		}

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

	public void initInstruments(OneMarketData marketData) {

		short marketType = marketData.getBourseInfo().getMarketType();

		List<Instrument> ins = null;

		if (isMarketBourse(marketType, STOCK_MARKET, SH_BOURSE)) {
			ins = getInstruments();

		} else if (isMarketBourse(marketType, STOCK_MARKET, SZ_BOURSE)) {
			ins = getInstruments();

		} else if (isMarketBourse(marketType, FUTURES_MARKET, DALIAN_BOURSE)) {
//			ins = getInstruments();

		} else if (isMarketBourse(marketType, FUTURES_MARKET, SHANGHAI_BOURSE)) {
//			ins = getInstruments();

		} else if (isMarketBourse(marketType, FUTURES_MARKET, ZHENGZHOU_BOURSE)) {
//			ins = getInstruments();

		} else if (isMarketBourse(marketType, FUTURES_MARKET, HUANGJIN_BOURSE)) {
//			ins = getInstruments();

		} else if (isMarketBourse(marketType, FUTURES_MARKET, GUZHI_BOURSE)) {

//			ins = getInstruments();
		} else if (isMarketBourse(marketType, HK_MARKET, HK_BOURSE)) {
//			ins = getInstruments();

		} else if (isMarketBourse(marketType, HK_MARKET, GE_BOURSE)) {
//			ins = getInstruments();
		} else if (isMarketBourse(marketType, HK_MARKET, INDEX_BOURSE)) {
//			ins = getInstruments();
		} else {

		}

		if (ins != null) {
			for (StockInitInfo stock : marketData.getStockInitInfos()) {
				Instrument instrument = new Instrument(stock);
				// logger.debug("{}",instrument);
				ins.add(instrument);

				getInstrumentMap().put(instrument.getSymbol(), instrument);
			}

		}

	}

	public Instrument getInstrument(String symbol) {
		return getInstrumentMap().get(symbol);
	}

	public Instrument subscribe(String symbol){
		Instrument instrument = getInstrument(symbol);
		logger.debug("instrument:{}", instrument);
		if (instrument != null) {
			CodeInfo code = new CodeInfo(instrument.getMarket(),
					instrument.getCode());
			quoteClientHandler.reqTrend(code);
			quoteClientHandler.reqStockTick(code);
			
			codes.add(code);
			quoteClientHandler.reqRealTime(codes);
			quoteClientHandler.reqAutoPush(codes);
		}
		return instrument;

	}
	
	public void kline(String symbol, PeriodType period, int day){
		Instrument instrument = getInstrument(symbol);
		if (instrument != null) {
			CodeInfo code = new CodeInfo(instrument.getMarket(),
					instrument.getCode());
			quoteClientHandler.reqDayData(code, period, day);
		}
	}

}
