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

public class AnsTick extends Envelope {

	private StockOtherData otherData;

	private ShareRealTimeData data;

	private int size;

	private List<Tick> ticks;

	public AnsTick(ByteBuf byteBuf) {
		super(EnvelopeType.RT_TICK, byteBuf);

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
		size = byteBuf.readInt();

		ticks = new ArrayList<Tick>(size);

		for (int i = 0; i < size; i++) {
			ticks.add(new Tick(byteBuf));
		}

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
