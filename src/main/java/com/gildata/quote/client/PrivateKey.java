package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

public class PrivateKey {
	
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
		if (codeInfo != null){
			codeInfo.encodeAsByteBuf(byteBuf);
		}else{
			byteBuf.writeZero(8);
		}
		
	}

	@Override
	public String toString() {
		return "PrivateKey [codeInfo=" + codeInfo + "]";
	}
	
	
	

}
