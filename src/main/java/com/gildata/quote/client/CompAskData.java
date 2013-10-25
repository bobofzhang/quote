package com.gildata.quote.client;

import java.nio.ByteOrder;
import java.util.Arrays;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class CompAskData extends Envelope{

	private Envelope[] asks;

	public CompAskData(Envelope[] asks) {
		super(EnvelopeType.RT_COMPASKDATA);
		this.asks = asks;
	}

	@Override
	public void encode(ByteBuf byteBuf) {
		if (asks != null) {
			byteBuf.writeShort(asks.length);
			
			for (Envelope ask:asks){
				ByteBuf data = Unpooled.buffer(512).order(ByteOrder.LITTLE_ENDIAN);
				ask.encodeAsByteBuf(data);
				byteBuf.writeShort(data.readableBytes());
				
			}
			for (Envelope ask:asks){
				ask.encodeAsByteBuf(byteBuf);
			}
		}	
	}

	@Override
	public String toString() {
		return "CompAskData [asks=" + Arrays.toString(asks) + "]";
	}
	
	
	
}
