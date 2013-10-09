package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.FLAG;
import static com.gildata.quote.client.QuoteConstants.HEADER_LEN;
import io.netty.buffer.ByteBuf;

public class Envelope {
	
	//private static final Logger logger = LoggerFactory.getLogger(Envelope.class);

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
		encodeHeader(byteBuf);
		encodeBody(byteBuf);
	}
	


	public void encodeHeader(ByteBuf byteBuf) {
		byteBuf.writeBytes(FLAG);
		byteBuf.writeInt(getLength());
		byteBuf.writeShort(type.getValue());
		byteBuf.writeByte(index);
		byteBuf.writeByte(operator);
		byteBuf.writeInt(key);
		if (privateKey != null) {
			privateKey.encodeAsByteBuf(byteBuf);
		} else {
			byteBuf.writeZero(8);
		}

	}

	public int getLength() {
		return HEADER_LEN + getBodyLength();
	}

	public int getBodyLength(){
		return 0;
	}

	public void encodeBody(ByteBuf byteBuf){
		
	}

	@Override
	public String toString() {
		return "Envelope [type=" + type + ", index=" + index + ", operator="
				+ operator + ", key=" + key + ", privateKey=" + privateKey
				+ "]";
	}
	
	

}
