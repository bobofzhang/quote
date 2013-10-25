package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

public class ReqLimitTick extends Envelope{
	
	private CodeInfo code;
	private short count;
	
	public ReqLimitTick() {
		super(EnvelopeType.RT_LIMITTICK);
	}
	
	
	
	public ReqLimitTick(CodeInfo code, short count) {
		super(EnvelopeType.RT_LIMITTICK);
		this.code = code;
		this.count = count;
	}



	public CodeInfo getCode() {
		return code;
	}
	public void setCode(CodeInfo code) {
		this.code = code;
	}
	public short getCount() {
		return count;
	}
	public void setCount(short count) {
		this.count = count;
	}
	
	@Override
	public void encode(ByteBuf byteBuf) {
		byteBuf.writeShort(2);
		byteBuf.writeShort(0);	
		QuoteUtils.writeEncodable(byteBuf, code, 8);
		byteBuf.writeShort(count);
		byteBuf.writeShort(0);


	}

}
