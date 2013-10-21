package com.gildata.quote.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.gildata.quote.client.QuoteUtils;
import com.gildata.quote.client.StockInitInfo;

public class Instrument {

	private String symbol;

	private String code;

	private String name;

	private short market;

	private String abbr;

	private String[] tokens;

	private BigDecimal prevClose;

	private BigDecimal lastPrice;

	public Instrument(StockInitInfo info) {
		super();
		this.symbol = info.getStockCode().toSymbol();
		this.code = info.getStockCode().getCode();
		this.market = info.getStockCode().getMarket();
		this.name = info.getStockName();
		this.prevClose = BigDecimal
				.valueOf((double) info.getPrevClose() / 1000);

		this.abbr = QuoteUtils.toPingYinFirstChar(info.getStockName());

		this.tokens = new String[] { symbol, abbr };

	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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

	public short getMarket() {
		return market;
	}

	public void setMarket(short market) {
		this.market = market;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String[] getTokens() {
		return tokens;
	}

	public void setTokens(String[] tokens) {
		this.tokens = tokens;
	}

	public BigDecimal getPrevClose() {
		return prevClose;
	}

	public void setPrevClose(BigDecimal prevClose) {
		this.prevClose = prevClose;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SIMPLE_STYLE);

	}

}
