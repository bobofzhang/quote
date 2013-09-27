package com.gildata.quote.client;

import java.util.Arrays;

import io.netty.buffer.ByteBuf;

public class CompAskData extends Envelope{

	private Envelope[] asks;

	public CompAskData(Envelope[] asks) {
		super(EnvelopeType.RT_COMPASKDATA);
		this.asks = asks;
	}

	@Override
	public int getBodyLength() {
		int len = 0;
		if (asks != null) {
			len = 2 + 2 * asks.length;
			for (Envelope ask:asks){
				len+=ask.getLength();
			}
		}
		return len;
	}

	@Override
	public void encodeBody(ByteBuf byteBuf) {
		if (asks != null) {
			byteBuf.writeShort(asks.length);
			
			for (Envelope ask:asks){
				byteBuf.writeShort(ask.getLength());
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
