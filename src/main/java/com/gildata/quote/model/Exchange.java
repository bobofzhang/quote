package com.gildata.quote.model;

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
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Exchange {

	public static final Exchange SH_STOCK_EXCHANGE = new Exchange("SH",
			"上海股票交易所");
	public static final Exchange SZ_STOCK_EXCHANGE = new Exchange("SZ",
			"深圳股票交易所");
	public static final Exchange HK_STOCK_EXCHANGE = new Exchange("HK",
			"香港股票交易所");
	public static final Exchange DL_FUTURES_EXCHANGE = new Exchange("DL",
			"大连期货交易所");
	public static final Exchange SH_FUTURES_EXCHANGE = new Exchange("SF",
			"上海期货交易所");
	public static final Exchange ZZ_FUTURES_EXCHANGE = new Exchange("ZZ",
			"郑州期货交易所");
	public static final Exchange GD_FUTURES_EXCHANGE = new Exchange("GD",
			"黄金交易所");
	public static final Exchange IF_FUTURES_EXCHANGE = new Exchange("IF",
			"金融期货交易所");

	
	public static Exchange getExchange(short marketType){
		if (isMarketBourse(marketType, STOCK_MARKET, SH_BOURSE)) {
			return Exchange.SH_STOCK_EXCHANGE;

		} else if (isMarketBourse(marketType, STOCK_MARKET, SZ_BOURSE)) {
			return Exchange.SZ_STOCK_EXCHANGE;

		} else if (isMarketBourse(marketType, FUTURES_MARKET, DALIAN_BOURSE)) {
			return Exchange.DL_FUTURES_EXCHANGE;

		} else if (isMarketBourse(marketType, FUTURES_MARKET, SHANGHAI_BOURSE)) {
			return Exchange.SH_FUTURES_EXCHANGE;

		} else if (isMarketBourse(marketType, FUTURES_MARKET, ZHENGZHOU_BOURSE)) {
			return Exchange.ZZ_FUTURES_EXCHANGE;

		} else if (isMarketBourse(marketType, FUTURES_MARKET, HUANGJIN_BOURSE)) {
			return Exchange.GD_FUTURES_EXCHANGE;

		} else if (isMarketBourse(marketType, FUTURES_MARKET, GUZHI_BOURSE)) {
			return Exchange.IF_FUTURES_EXCHANGE;

		} else if (isMarketBourse(marketType, HK_MARKET, HK_BOURSE)) {
			return Exchange.HK_STOCK_EXCHANGE;

		} else if (isMarketBourse(marketType, HK_MARKET, GE_BOURSE)) {

		} else if (isMarketBourse(marketType, HK_MARKET, INDEX_BOURSE)) {

		} else {

		}	
		return null;
	};
	
	private String code;
	private String name;
	private int date;
	private List<Integer> times;

	public Exchange(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public List<Integer> getTimes() {
		if (times == null){
			times = new ArrayList<Integer>();
		}
		return times;
	}

	public void setTimes(List<Integer> times) {
		this.times = times;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
