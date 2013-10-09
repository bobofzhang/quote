package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.CODE_LEN;
import io.netty.buffer.ByteBuf;

public class CodeInfo {
	private short market;
	private String code;

	public CodeInfo() {
		super();
	}
	public CodeInfo(ByteBuf buf) {
		super();
		this.market = buf.readShort();
		byte[] c = new byte[6];
		buf.readBytes(c);
		this.code = new String(c);
	}
	
	
	public CodeInfo(short market, String code) {
		super();
		this.market = market;
		this.code = code;
	}

	public int getMarket() {
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
		if (code != null) {
			byte[] b = code.getBytes();
			int len = b.length;
			if (len > CODE_LEN) {
				byteBuf.writeBytes(b, 0, CODE_LEN);
			} else {
				byteBuf.writeBytes(b);
				if (len < CODE_LEN) {
					byteBuf.writeZero(CODE_LEN - len);
				}
			}
		} else {
			byteBuf.writeZero(CODE_LEN);
		}

	}

	@Override
	public String toString() {
		return "CodeInfo [market=" + market + ", code=" + code + "]";
	}

}
