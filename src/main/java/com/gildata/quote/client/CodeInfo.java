package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.GB18030;
import static com.gildata.quote.client.QuoteConstants.CODE_LEN;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.netty.buffer.ByteBuf;

public class CodeInfo implements Encodable{
	private short market;
	private String code;

	public CodeInfo() {
		super();
	}
	public CodeInfo(ByteBuf byteBuf) {
		super();
		this.market = byteBuf.readShort();
		this.code = QuoteUtils.readString(byteBuf, CODE_LEN, GB18030);
	}
	
	
	public CodeInfo(short market, String code) {
		super();
		this.market = market;
		this.code = code;
	}

	public short getMarket() {
		return market;
	}

	public void setMarket(short market) {
		this.market = market;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void encodeAsByteBuf(ByteBuf byteBuf) {
		byteBuf.writeShort(market);
		QuoteUtils.writeString(byteBuf, code, CODE_LEN, GB18030);


	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
