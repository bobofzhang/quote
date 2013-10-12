package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

public class ServerCompare implements Encodable{
	
	private short bourse = MarketType.STOCK_MARKET;
	private int crc;
	
	
	public short getBourse() {
		return bourse;
	}
	public void setBourse(short bourse) {
		this.bourse = bourse;
	}
	public int getCrc() {
		return crc;
	}
	public void setCrc(int crc) {
		this.crc = crc;
	}
	
	public void encodeAsByteBuf(ByteBuf byteBuf) {
		byteBuf.writeShort(bourse);
		byteBuf.writeShort(0);
		byteBuf.writeInt(crc);
		
	}
	
	@Override
	public String toString() {
		return "ServerCompare [bourse=" + bourse + ", crc=" + crc + "]";
	}
	
	

}
