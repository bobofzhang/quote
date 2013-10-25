package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StockOtherData {

	private short time;
	private short second;

	private int current; // 现在总手
	private int sellVol; // 外盘
	private int buyVol; // 内盘
	private int value1; // 今开仓,深交所股票单笔成交数,港股交易宗数 对于外汇时，昨收盘数据
	private int value2; // 对于外汇时，报价状态 今平仓

	public StockOtherData(ByteBuf byteBuf) {
		super();
		this.time = byteBuf.readShort();
		this.second = byteBuf.readShort();
		this.current = byteBuf.readInt();
		this.sellVol = byteBuf.readInt();
		this.buyVol = byteBuf.readInt();
		this.value1 = byteBuf.readInt();
		this.value2 = byteBuf.readInt();
	}

	public short getTime() {
		return time;
	}

	public void setTime(short time) {
		this.time = time;
	}

	public short getSecond() {
		return second;
	}

	public void setSecond(short second) {
		this.second = second;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getSellVol() {
		return sellVol;
	}

	public void setSellVol(int sellVol) {
		this.sellVol = sellVol;
	}

	public int getBuyVol() {
		return buyVol;
	}

	public void setBuyVol(int buyVol) {
		this.buyVol = buyVol;
	}
	
	

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
