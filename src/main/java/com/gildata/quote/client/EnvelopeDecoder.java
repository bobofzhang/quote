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
		logger.debug("decode type:{}", type);
		switch (type) {
		case RT_ZIPDATA:
			return getObject(decompress(frame));
		case RT_LOGIN:
		case RT_LOGIN_HK:
		case RT_LOGIN_FUTURES:
		case RT_LOGIN_FOREIGN:
		case RT_LOGIN_WP:
		case RT_LOGIN_INFO:
		case RT_LOGIN_LEVEL:
			return new AnsLogin(type, frame);
		case RT_INITIALINFO:
			return new AnsInitialData(frame);
		case RT_SERVERINFO:
			return new AnsServerInfo(frame);
		case RT_BULLETIN:
			return new AnsBulletin(frame);
		case RT_REALTIME:
			return new AnsRealTime(frame);
		case RT_AUTOPUSH:
			return new AnsAutoPush(frame);
		case RT_TREND:
			return new AnsTrendData(frame);
		case RT_STOCKTICK:
			return new AnsStockTick(frame);
		case RT_LIMITTICK:
			return new AnsStockTick(type, frame);
		case RT_TICK:
			return new AnsTick(frame);
		case RT_KEEPACTIVE:
			return new AnsKeepActive(frame);
		case RT_TECHDATA:
			return new AnsDayData(frame);
		case RT_TECHDATA_EX:
			return new AnsDayDataEx(frame);			
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
		logger.trace("decompressed : {}", HexDump.dump(decompressed));

		ByteBuf out = Unpooled.buffer(numBytes);
		out.writeBytes(decompressed, 0, numBytes);
		return out;

	}

}
