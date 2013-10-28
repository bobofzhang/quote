package com.gildata.quote.model;


public class Instrument {
	
	private Ticker ticker;
	private Exchange exchange;
	
	public Instrument(Ticker ticker) {
		super();
		this.ticker = ticker;
		this.exchange = Exchange.getExchange(ticker.getMarket());
	}
	
	
	public Ticker getTicker() {
		return ticker;
	}
	public void setTicker(Ticker ticker) {
		this.ticker = ticker;
	}
	public Exchange getExchange() {
		return exchange;
	}
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}
	
	

}
