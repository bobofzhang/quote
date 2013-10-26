package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AskData extends Envelope {


	private CodeInfo[] codes;
	
	public AskData(EnvelopeType type) {
		super(type);
	}

	public AskData(EnvelopeType type, CodeInfo[] codes) {
		super(type);
		this.codes = codes;
	}
	
	public AskData(EnvelopeType type, Collection<CodeInfo> codes) {
		this(type,codes.toArray(new CodeInfo[codes.size()]));
	}

	public CodeInfo[] getCodes() {
		return codes;
	}

	public void setCodes(CodeInfo[] codes) {
		this.codes = codes;
	}

	@Override
	public void encode(ByteBuf byteBuf) {
		if (codes != null) {
			byteBuf.writeShort(codes.length);
			byteBuf.writeShort(0);
			for (CodeInfo c : codes) {
				c.encodeAsByteBuf(byteBuf);
			}

		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	

}
