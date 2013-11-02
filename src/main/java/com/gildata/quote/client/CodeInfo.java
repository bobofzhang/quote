package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.CODE_LEN;
import static com.gildata.quote.client.QuoteConstants.GB18030;
import io.netty.buffer.ByteBuf;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

;

public class CodeInfo implements Encodable {
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
	
	public String getMarketCode() {
		return QuoteUtils.toMarketCode(market);
	}

	public String toSymbol() {

		String ex = getMarketCode();
		if (StringUtils.isEmpty(ex)) {
			return code;
		} else {
			return code + "." + ex;
		}

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
