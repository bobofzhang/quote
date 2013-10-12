package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.FLAG;
import static com.gildata.quote.client.QuoteConstants.HEADER_LEN;

import java.nio.ByteOrder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Envelope implements Encodable{

	protected final EnvelopeType type;
	protected byte index;
	protected byte operator;
	protected int key;
	protected PrivateKey privateKey;

	public Envelope(EnvelopeType type) {
		super();
		this.type = type;
	}

	public Envelope(EnvelopeType type, byte index, byte operator, int key,
			PrivateKey privateKey) {
		super();
		this.type = type;
		this.index = index;
		this.operator = operator;
		this.key = key;
		this.privateKey = privateKey;
	}
	
	public Envelope(EnvelopeType type,ByteBuf buf) {
		this(type);
		this.index = buf.readByte();
		this.operator = buf.readByte();
		this.key = buf.readInt();
		this.privateKey = new PrivateKey(buf);
		
	}

	public EnvelopeType getType() {
		return type;
	}

	public byte getIndex() {
		return index;
	}

	public void setIndex(byte index) {
		this.index = index;
	}

	public byte getOperator() {
		return operator;
	}

	public void setOperator(byte operator) {
		this.operator = operator;
	}

	public long getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public void encodeAsByteBuf(ByteBuf byteBuf) {

		ByteBuf data = Unpooled.buffer(512).order(ByteOrder.LITTLE_ENDIAN);
		encode(data);

		byteBuf.writeBytes(FLAG);
		byteBuf.writeInt(HEADER_LEN + data.readableBytes());
		byteBuf.writeShort(type.getValue());
		byteBuf.writeByte(index);
		byteBuf.writeByte(operator);
		byteBuf.writeInt(key);
		QuoteUtils.writeEncodable(byteBuf, privateKey, 8);
		byteBuf.writeBytes(data);
		
	}

	public void encode(ByteBuf byteBuf){
		
	}

	@Override
	public String toString() {
		return "Envelope [type=" + type + ", index=" + index + ", operator="
				+ operator + ", key=" + key + ", privateKey=" + privateKey
				+ "]";
	}
	
	

}
