package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AnsDayData extends Envelope {

	private int size;

	private List<StockCompDayData> datas;
	
	public AnsDayData(ByteBuf byteBuf) {
		super(EnvelopeType.RT_TECHDATA, byteBuf);

		size = byteBuf.readInt();

		datas = new ArrayList<StockCompDayData>(size);

		for (int i = 0; i < size; i++) {
			datas.add(new StockCompDayData(byteBuf));
		}

	}
	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<StockCompDayData> getDatas() {
		return datas;
	}

	public void setDatas(List<StockCompDayData> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
