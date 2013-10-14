package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.GB18030;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.netty.buffer.ByteBuf;

public class AnsBulletin extends Envelope {

	private int size;

	private String data;

	public AnsBulletin() {
		super(EnvelopeType.RT_BULLETIN);
	}

	public AnsBulletin(ByteBuf byteBuf) {
		super(EnvelopeType.RT_BULLETIN, byteBuf);
		this.size = byteBuf.readInt();
		this.data = byteBuf.toString(GB18030).trim();

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
