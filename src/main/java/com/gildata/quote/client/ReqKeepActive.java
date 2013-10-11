package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.FLAG;
import io.netty.buffer.ByteBuf;

public class ReqKeepActive extends Envelope{

	public ReqKeepActive() {
		super(EnvelopeType.RT_KEEPACTIVE);
	}

	@Override
	public void encodeAsByteBuf(ByteBuf byteBuf) {
		byteBuf.writeBytes(FLAG);
		byteBuf.writeInt(4);
		byteBuf.writeShort(type.getValue());
		byteBuf.writeByte(index);
		byteBuf.writeByte(operator);
	}
	
	@Override
	public String toString() {
		return "ReqKeepActive [type=" + type + ", index=" + index
				+ ", operator=" + operator + "]";
	}
	
	
	
}
