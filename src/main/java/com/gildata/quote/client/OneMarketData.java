package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OneMarketData implements Encodable {

	private CommBourseInfo bourseInfo;

	private List<StockInitInfo> stockInitInfos;

	public OneMarketData(ByteBuf buf) {
		super();
		this.bourseInfo = new CommBourseInfo(buf);

		short size = buf.readShort();
		buf.skipBytes(2);

		stockInitInfos = new ArrayList<StockInitInfo>(size);

		for (int i = 0; i < size; i++) {
			stockInitInfos.add(new StockInitInfo(buf));
		}

	}

	public CommBourseInfo getBourseInfo() {
		return bourseInfo;
	}

	public void setBourseInfo(CommBourseInfo bourseInfo) {
		this.bourseInfo = bourseInfo;
	}

	public List<StockInitInfo> getStockInitInfos() {
		if (stockInitInfos == null){
			stockInitInfos = new ArrayList<StockInitInfo>();
		}
		return stockInitInfos;
	}

	public void setStockInitInfos(List<StockInitInfo> stockInitInfos) {
		this.stockInitInfos = stockInitInfos;
	}

	@Override
	public void encodeAsByteBuf(ByteBuf byteBuf) {
		bourseInfo.encodeAsByteBuf(byteBuf);
		byteBuf.writeShort(getStockInitInfos().size());
		byteBuf.writeShort(0);
		for (StockInitInfo stockInitInfo:getStockInitInfos()){
			stockInitInfo.encodeAsByteBuf(byteBuf);
		}

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	

}
