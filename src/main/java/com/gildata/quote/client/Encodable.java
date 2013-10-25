package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

public interface Encodable {
	
	void encodeAsByteBuf(ByteBuf byteBuf);
}
