package com.gildata.quote.client;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.netty.buffer.ByteBuf;

public class AnsKeepActive extends Envelope {
	
	private int time;
	
	public AnsKeepActive() {
		super(EnvelopeType.RT_KEEPACTIVE);
	}
	
	public AnsKeepActive(ByteBuf byteBuf) {
		super(EnvelopeType.RT_KEEPACTIVE);
		this.index = byteBuf.readByte();
		this.operator = byteBuf.readByte();
		this.time = byteBuf.readInt();
	}
	
	

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
