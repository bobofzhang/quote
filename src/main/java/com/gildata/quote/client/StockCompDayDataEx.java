package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StockCompDayDataEx {
	private int date; // 时间
	private int open; // 开盘价
	private int high; // 最高价
	private int low; // 最低价
	private int close; // 收盘价
	private int amount; // 成交金额
	private int volume; // 成交量
	private int debtRatio; // 国债利率(单位为0.1分),基金净值(单位为0.1分), 无意义时，须将其设为0
	
	
	public StockCompDayDataEx(ByteBuf byteBuf) {
		super();
		this.date = byteBuf.readInt();
		this.open = byteBuf.readInt();
		this.high = byteBuf.readInt();
		this.low = byteBuf.readInt();
		this.close = byteBuf.readInt();
		this.amount = byteBuf.readInt();
		this.volume = byteBuf.readInt();
		this.debtRatio = byteBuf.readInt();
	}
	
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
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
	public int getClose() {
		return close;
	}
	public void setClose(int close) {
		this.close = close;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getDebtRatio() {
		return debtRatio;
	}
	public void setDebtRatio(int debtRatio) {
		this.debtRatio = debtRatio;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	
}
