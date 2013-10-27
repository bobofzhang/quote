package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AnsDayDataEx extends Envelope {

	private int size;

	private List<StockCompDayDataEx> datas;

	public AnsDayDataEx(ByteBuf byteBuf) {
		super(EnvelopeType.RT_TECHDATA_EX, byteBuf);

		size = byteBuf.readInt();

		datas = new ArrayList<StockCompDayDataEx>(size);

		for (int i = 0; i < size; i++) {
			datas.add(new StockCompDayDataEx(byteBuf));
		}

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<StockCompDayDataEx> getDatas() {
		return datas;
	}

	public void setDatas(List<StockCompDayDataEx> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
