package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.GB18030;
import io.netty.buffer.ByteBuf;

public class AnsLogin extends Envelope {
	
	private short error;
	
	private short size;
	
	private String ret;

	public AnsLogin() {
		super(EnvelopeType.RT_LOGIN);
	}

	public AnsLogin(ByteBuf buf) {
		super(EnvelopeType.RT_LOGIN, buf);
		this.error = buf.readShort();
		this.size = buf.readShort();
		
		this.ret = buf.toString(GB18030);

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
		return "AnsLogin [error=" + error + ", size=" + size + ", ret=" + ret
				+ "]";
	}

	

}
