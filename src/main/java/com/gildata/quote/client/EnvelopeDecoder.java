package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.ByteOrder;
import java.util.zip.Inflater;

import org.springframework.stereotype.Component;

@Component
public class EnvelopeDecoder extends LengthFieldBasedFrameDecoder {

	private static final InternalLogger logger = InternalLoggerFactory
			.getInstance(EnvelopeDecoder.class);
	
	private static final String name = "ENVELOPE_DECODER";
	

	public EnvelopeDecoder() {
		this(1048576);
	}

	public EnvelopeDecoder(int maxFrameLength) {
		super(ByteOrder.LITTLE_ENDIAN, maxFrameLength, 4, 4, 0, 8, true);
	}
	
    public static String getName() {
        return name;
    }

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in)
			throws Exception {
		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if (frame == null) {
			return null;
		}

		return getObject(frame);
	}

	private Object getObject(ByteBuf frame) throws Exception {
		frame = frame.order(ByteOrder.LITTLE_ENDIAN);
		EnvelopeType type = EnvelopeType.fromValue(frame.readShort());
		logger.debug("decode type:{}",type);
		switch (type) {
		case RT_ZIPDATA:
			return getObject(decompress(frame));
		case RT_LOGIN:		
			return new AnsLogin(frame);

		default:
			return null;

		}

	}

	private ByteBuf decompress(ByteBuf in) throws Exception {

		in.skipBytes(2); 

		int zipLen = in.readInt();
		int orgLen = in.readInt();

		byte[] compressed = new byte[zipLen];
		byte[] decompressed = new byte[orgLen];
		in.readBytes(compressed);
		Inflater inflater = new Inflater();
		inflater.setInput(compressed);
		int numBytes = inflater.inflate(decompressed);
		inflater.end();
		logger.debug("decompressed : {}", HexDump.dump(decompressed));
		
		ByteBuf out = Unpooled.buffer(numBytes);
		out.writeBytes(decompressed, 0, numBytes);
		return out;

	}

}
