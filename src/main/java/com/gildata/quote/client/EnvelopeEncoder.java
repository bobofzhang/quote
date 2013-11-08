package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.ByteOrder;

//@Component
@Sharable
public class EnvelopeEncoder extends MessageToByteEncoder<Envelope> {

	private static final InternalLogger logger = InternalLoggerFactory
			.getInstance(EnvelopeEncoder.class);
	
	private static final String name = "ENVELOPE_ENCODER";

    public static String getName() {
        return name;
    }
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Envelope msg, ByteBuf out)
			throws Exception {
		logger.debug("encode: {}",msg);
		
		msg.encodeAsByteBuf(out.order(ByteOrder.LITTLE_ENDIAN));
		
	}

}
