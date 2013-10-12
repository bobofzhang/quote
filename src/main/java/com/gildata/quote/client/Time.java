package com.gildata.quote.client;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.netty.buffer.ByteBuf;

public class Time implements Encodable {

	private short openTime;
	private short closeTime;

	public Time(ByteBuf buf) {
		super();
		this.openTime = buf.readShort();
		this.closeTime = buf.readShort();
	}
	
	public short getOpenTime() {
		return openTime;
	}

	public void setOpenTime(short openTime) {
		this.openTime = openTime;
	}

	public short getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(short closeTime) {
		this.closeTime = closeTime;
	}

	@Override
	public void encodeAsByteBuf(ByteBuf byteBuf) {
		
		byteBuf.writeShort(openTime);
		byteBuf.writeShort(closeTime);
		
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);

	}
	
	

}
