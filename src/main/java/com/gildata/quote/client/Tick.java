package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Tick {

	private short time;
	private byte direction;
	private byte second;
	private int price;
	private int vol;
	private int bid;
	private int ask;
	private int position;
	
	public Tick(ByteBuf byteBuf) {
		super();

		this.time = byteBuf.readShort();
		this.direction = byteBuf.readByte();
		this.second = byteBuf.readByte();
		this.price = byteBuf.readInt();
		this.vol = byteBuf.readInt();
		this.bid = byteBuf.readInt();
		this.ask = byteBuf.readInt();
		this.position = byteBuf.readInt();
	}
	
	public short getTime() {
		return time;
	}
	public void setTime(short time) {
		this.time = time;
	}
	public byte getDirection() {
		return direction;
	}
	public void setDirection(byte direction) {
		this.direction = direction;
	}
	public byte getSecond() {
		return second;
	}
	public void setSecond(byte second) {
		this.second = second;
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
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getAsk() {
		return ask;
	}
	public void setAsk(int ask) {
		this.ask = ask;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
