package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.FLAG;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	
	
}
