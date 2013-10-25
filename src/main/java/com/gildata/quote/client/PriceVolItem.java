package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PriceVolItem {
	
	private int price;
	
	private int vol;
	
	public PriceVolItem(ByteBuf byteBuf) {
		super();
		this.price = byteBuf.readInt();
		this.vol = byteBuf.readInt();
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
