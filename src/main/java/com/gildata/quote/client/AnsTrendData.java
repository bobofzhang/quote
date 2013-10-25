package com.gildata.quote.client;

import static com.gildata.quote.client.MarketType.FUTURES_MARKET;
import static com.gildata.quote.client.MarketType.HK_MARKET;
import static com.gildata.quote.client.MarketType.KIND_INDEX;
import static com.gildata.quote.client.MarketType.makeMarket;
import static com.gildata.quote.client.MarketType.makeSubMarket;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AnsTrendData extends Envelope {

	private short size;

	private StockOtherData otherData;

	private ShareRealTimeData data;

	private List<PriceVolItem> items;
	
	public AnsTrendData() {
		super(EnvelopeType.RT_TREND);
	}
	
	public AnsTrendData(ByteBuf byteBuf) {
		super(EnvelopeType.RT_TREND, byteBuf);
		
	    size = byteBuf.readShort();
	    byteBuf.skipBytes(2);
		
		this.otherData = new StockOtherData(byteBuf);
		CodeInfo c = getPrivateKey().getCodeInfo();
		if (makeSubMarket(c.getMarket()) == KIND_INDEX) {
			this.data = new IndexRealTime(byteBuf);
		} else if (makeMarket(c.getMarket()) == FUTURES_MARKET) {
			this.data = new FuturesRealTime(byteBuf);
		} else if (makeMarket(c.getMarket()) == HK_MARKET) {
			this.data = new HKStockRealTime(byteBuf);
		} else {
			this.data = new StockRealTime(byteBuf);
		}
		
		items = new ArrayList<PriceVolItem>(size);

		for (int i = 0; i < size; i++) {
			items.add(new PriceVolItem(byteBuf));
		}

	}

	public short getSize() {
		return size;
	}

	public void setSize(short size) {
		this.size = size;
	}

	public StockOtherData getOtherData() {
		return otherData;
	}

	public void setOtherData(StockOtherData otherData) {
		this.otherData = otherData;
	}

	public ShareRealTimeData getData() {
		return data;
	}

	public void setData(ShareRealTimeData data) {
		this.data = data;
	}



	public List<PriceVolItem> getItems() {
		return items;
	}

	public void setItems(List<PriceVolItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
