package com.gildata.quote.client;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.netty.buffer.ByteBuf;

public class PrivateKey implements Encodable{
	
	private CodeInfo codeInfo;
	
	public PrivateKey(CodeInfo codeInfo) {
		super();
		this.codeInfo = codeInfo;
	}
	
	public PrivateKey(ByteBuf buf) {
		super();
		this.codeInfo = new CodeInfo(buf);
	}

	public CodeInfo getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(CodeInfo codeInfo) {
		this.codeInfo = codeInfo;
	}
	
	
	public void encodeAsByteBuf(ByteBuf byteBuf) {
		QuoteUtils.writeEncodable(byteBuf, codeInfo, 8);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	
	

}
