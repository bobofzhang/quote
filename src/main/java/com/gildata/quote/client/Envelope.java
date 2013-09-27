package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.FLAG;
import static com.gildata.quote.client.QuoteConstants.HEADER_LEN;
import io.netty.buffer.ByteBuf;

public abstract class Envelope {

	protected final EnvelopeType type;
	protected byte index;
	protected byte operator;
	protected long key;
	protected PrivateKey privateKey;

	public Envelope(EnvelopeType type) {
		super();
		this.type = type;
	}

	public Envelope(EnvelopeType type, byte index, byte operator, long key,
			PrivateKey privateKey) {
		super();
		this.type = type;
		this.index = index;
		this.operator = operator;
		this.key = key;
		this.privateKey = privateKey;
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

	public void setKey(long key) {
		this.key = key;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public void encodeAsByteBuf(ByteBuf byteBuf) {
		encodeHeader(byteBuf);
		encodeBody(byteBuf);
	}

	public void encodeHeader(ByteBuf byteBuf) {

		byteBuf.writeBytes(FLAG);
		byteBuf.writeInt(getLength());
		byteBuf.writeShort(type.getValue());
		byteBuf.writeByte(index);
		byteBuf.writeByte(operator);
		byteBuf.writeLong(key);
		if (privateKey != null) {
			privateKey.encodeAsByteBuf(byteBuf);
		} else {
			byteBuf.writeZero(8);
		}

	}

	public int getLength() {
		return HEADER_LEN + getBodyLength();
	}

	public abstract int getBodyLength();

	public abstract void encodeBody(ByteBuf byteBuf);

}
