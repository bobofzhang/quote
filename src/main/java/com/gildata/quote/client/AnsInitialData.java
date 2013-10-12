package com.gildata.quote.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.netty.buffer.ByteBuf;

public class AnsInitialData extends Envelope{
	
	private short size;
	
	private short opertion;
	
	private List<OneMarketData> marketDatas;

	public AnsInitialData() {
		super(EnvelopeType.RT_INITIALINFO);
	}
	
	public AnsInitialData(ByteBuf buf) {
		super(EnvelopeType.RT_INITIALINFO, buf);
		
	    size = buf.readShort();
		opertion = buf.readShort();
		
		marketDatas = new ArrayList<OneMarketData>(size);

		for (int i = 0; i < size; i++) {
			marketDatas.add(new OneMarketData(buf));
		}

	}
	
	public short getSize() {
		return size;
	}

	public void setSize(short size) {
		this.size = size;
	}

	public short getOpertion() {
		return opertion;
	}

	public void setOpertion(short opertion) {
		this.opertion = opertion;
	}

	public List<OneMarketData> getMarketDatas() {
		if (marketDatas == null){
			marketDatas = new ArrayList<OneMarketData>();
		}
		return marketDatas;
	}

	public void setMarketDatas(List<OneMarketData> marketDatas) {
		this.marketDatas = marketDatas;
	}

	@Override
	public void encode(ByteBuf byteBuf) {
		byteBuf.writeShort(getMarketDatas().size());
		byteBuf.writeShort(0);
		for (OneMarketData marketData:getMarketDatas()){
			marketData.encodeAsByteBuf(byteBuf);
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}


	
	

}
