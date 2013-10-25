package com.gildata.quote.model;

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
