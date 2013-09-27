package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@ChannelHandler.Sharable
public class EnvelopeEncoder extends MessageToByteEncoder<Envelope> {

	private static final String name = "ENVELOPE_ENCODER";

    public static String getName() {
        return name;
    }
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Envelope msg, ByteBuf out)
			throws Exception {
		msg.encodeAsByteBuf(out);
		
	}

}
