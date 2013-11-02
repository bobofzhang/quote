package com.gildata.quote.model;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class InitData {
	
	private List<Ticker> tickers;
	
	private Map<String, Exchange> exchanges;
	
	

	public InitData() {
		super();
	}

	public InitData(List<Ticker> tickers, Map<String, Exchange> exchanges) {
		super();
		this.tickers = tickers;
		this.exchanges = exchanges;
	}

	public List<Ticker> getTickers() {
		return tickers;
	}

	public void setTickers(List<Ticker> tickers) {
		this.tickers = tickers;
	}

	public Map<String, Exchange> getExchanges() {
		return exchanges;
	}

	public void setExchanges(Map<String, Exchange> exchanges) {
		this.exchanges = exchanges;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
