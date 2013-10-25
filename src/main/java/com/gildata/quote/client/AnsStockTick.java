package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AnsStockTick extends Envelope {

	private int size;

	private List<Tick> ticks;

	public AnsStockTick(EnvelopeType type, ByteBuf byteBuf) {
		super(type, byteBuf);

		size = byteBuf.readInt();

		ticks = new ArrayList<Tick>(size);

		for (int i = 0; i < size; i++) {
			ticks.add(new Tick(byteBuf));
		}

	}

	public AnsStockTick(ByteBuf byteBuf) {
		this(EnvelopeType.RT_STOCKTICK, byteBuf);

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Tick> getTicks() {
		return ticks;
	}

	public void setTicks(List<Tick> ticks) {
		this.ticks = ticks;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
