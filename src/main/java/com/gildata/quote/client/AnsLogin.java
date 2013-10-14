package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.GB18030;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.netty.buffer.ByteBuf;

public class AnsLogin extends Envelope {
	
	private short error;
	
	private short size;
	
	private String ret;

	public AnsLogin() {
		super(EnvelopeType.RT_LOGIN);
	}
	
	public AnsLogin(EnvelopeType type) {
		super(type);
	}

	public AnsLogin(EnvelopeType type,ByteBuf byteBuf) {
		super(type, byteBuf);
		this.error = byteBuf.readShort();
		this.size = byteBuf.readShort();

		this.ret = byteBuf.toString(GB18030).trim();
	}
	
	public AnsLogin(ByteBuf byteBuf) {
		this(EnvelopeType.RT_LOGIN,byteBuf);
	}

	public short getError() {
		return error;
	}

	public void setError(short error) {
		this.error = error;
	}
	
	

	public short getSize() {
		return size;
	}

	public void setSize(short size) {
		this.size = size;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	

}
