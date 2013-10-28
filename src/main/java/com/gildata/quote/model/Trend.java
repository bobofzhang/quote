package com.gildata.quote.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.gildata.quote.client.AnsTrendData;
import com.gildata.quote.client.PriceVolItem;
import com.gildata.quote.client.ShareRealTimeData;
import com.gildata.quote.client.StockOtherData;

public class Trend {
	
	private int date;
	
	private List<Integer> times;
	
	private StockOtherData otherData;

	private ShareRealTimeData data;

	private List<PriceVolItem> items;
	
	
	
	

	public Trend(AnsTrendData msg) {
		super();
		Exchange ex = Exchange.getExchange(msg.getPrivateKey().getCodeInfo().getMarket());
		this.date = ex.getDate();
		this.times = ex.getTimes();
		this.data = msg.getData();
		this.otherData = msg.getOtherData();
		this.items = msg.getItems();
		
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public List<Integer> getTimes() {
		return times;
	}

	public void setTimes(List<Integer> times) {
		this.times = times;
	}

	public StockOtherData getOtherData() {
		return otherData;
	}

	public void setOtherData(StockOtherData otherData) {
		this.otherData = otherData;
	}

	public ShareRealTimeData getData() {
		return data;
	}

	public void setData(ShareRealTimeData data) {
		this.data = data;
	}

	public List<PriceVolItem> getItems() {
		return items;
	}

	public void setItems(List<PriceVolItem> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	

}
