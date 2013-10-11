package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

public class AnsKeepActive extends Envelope {
	
	private int time;
	
	public AnsKeepActive() {
		super(EnvelopeType.RT_KEEPACTIVE);
	}
	
	public AnsKeepActive(ByteBuf buf) {
		super(EnvelopeType.RT_KEEPACTIVE);
		this.index = buf.readByte();
		this.operator = buf.readByte();
		this.time = buf.readInt();
	}

	@Override
	public String toString() {
		return "AnsKeepActive [type=" + type + ", index=" + index
				+ ", operator=" + operator + ", time=" + time + "]";
	}

}
