package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FuturesRealTime implements ShareRealTimeData {

	private int open; // 今开盘
	private int high; // 最高价
	private int low; // 最低价
	private int price; // 最新价
	private int vol; // 成交量(单位:合约单位)
	private int openInterest; // 持仓量(单位:合约单位)

	private int bid; // 买一价
	private int bidSize; // 买一量

	private int ask; // 卖一价
	private int askSize; // 卖一量

	private int preSettlementPrice; // 昨结算价
	private int settlementPrice; // 现结算价

	private int close; // 今收盘

	private int highest; // 史最高
	private int lowest; // 史最低

	private int limitUp; // 涨停板
	private int limitDown; // 跌停板
	private int volUnit; // 每手股数
	private int preOpenInterest; // 昨持仓量(单位:合约单位)

	private int longOpen; // 多头开(单位:合约单位)
	private int longClose; // 多头平(单位:合约单位)
	private int shortOpen; // 空头开(单位:合约单位)
	private int shortClose; // 空头平(单位:合约单位)

	private int preClose; // 前收盘

	public FuturesRealTime(ByteBuf byteBuf) {
		super();
		this.open = byteBuf.readInt();
		this.high = byteBuf.readInt();
		this.low = byteBuf.readInt();
		this.price = byteBuf.readInt();
		this.vol = byteBuf.readInt();
		this.openInterest = byteBuf.readInt();

		this.bid = byteBuf.readInt();
		this.bidSize = byteBuf.readInt();
		this.ask = byteBuf.readInt();
		this.askSize = byteBuf.readInt();

		this.preSettlementPrice = byteBuf.readInt();
		this.settlementPrice = byteBuf.readInt();
		this.close = byteBuf.readInt();

		this.highest = byteBuf.readInt();
		this.lowest = byteBuf.readInt();

		this.limitUp = byteBuf.readInt();
		this.limitDown = byteBuf.readInt();
		this.volUnit = byteBuf.readInt();
		this.preOpenInterest = byteBuf.readInt();

		this.longOpen = byteBuf.readInt();
		this.longClose = byteBuf.readInt();
		this.shortOpen = byteBuf.readInt();
		this.shortClose = byteBuf.readInt();
		this.preClose = byteBuf.readInt();
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}

	public int getOpenInterest() {
		return openInterest;
	}

	public void setOpenInterest(int openInterest) {
		this.openInterest = openInterest;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getBidSize() {
		return bidSize;
	}

	public void setBidSize(int bidSize) {
		this.bidSize = bidSize;
	}

	public int getAsk() {
		return ask;
	}

	public void setAsk(int ask) {
		this.ask = ask;
	}

	public int getAskSize() {
		return askSize;
	}

	public void setAskSize(int askSize) {
		this.askSize = askSize;
	}

	public int getPreSettlementPrice() {
		return preSettlementPrice;
	}

	public void setPreSettlementPrice(int preSettlementPrice) {
		this.preSettlementPrice = preSettlementPrice;
	}

	public int getSettlementPrice() {
		return settlementPrice;
	}

	public void setSettlementPrice(int settlementPrice) {
		this.settlementPrice = settlementPrice;
	}

	public int getClose() {
		return close;
	}

	public void setClose(int close) {
		this.close = close;
	}

	public int getHighest() {
		return highest;
	}

	public void setHighest(int highest) {
		this.highest = highest;
	}

	public int getLowest() {
		return lowest;
	}

	public void setLowest(int lowest) {
		this.lowest = lowest;
	}

	public int getLimitUp() {
		return limitUp;
	}

	public void setLimitUp(int limitUp) {
		this.limitUp = limitUp;
	}

	public int getLimitDown() {
		return limitDown;
	}

	public void setLimitDown(int limitDown) {
		this.limitDown = limitDown;
	}

	public int getVolUnit() {
		return volUnit;
	}

	public void setVolUnit(int volUnit) {
		this.volUnit = volUnit;
	}

	public int getPreOpenInterest() {
		return preOpenInterest;
	}

	public void setPreOpenInterest(int preOpenInterest) {
		this.preOpenInterest = preOpenInterest;
	}

	public int getLongOpen() {
		return longOpen;
	}

	public void setLongOpen(int longOpen) {
		this.longOpen = longOpen;
	}

	public int getLongClose() {
		return longClose;
	}

	public void setLongClose(int longClose) {
		this.longClose = longClose;
	}

	public int getShortOpen() {
		return shortOpen;
	}

	public void setShortOpen(int shortOpen) {
		this.shortOpen = shortOpen;
	}

	public int getShortClose() {
		return shortClose;
	}

	public void setShortClose(int shortClose) {
		this.shortClose = shortClose;
	}

	public int getPreClose() {
		return preClose;
	}

	public void setPreClose(int preClose) {
		this.preClose = preClose;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
