package com.gildata.quote.client;

import static com.gildata.quote.client.MarketType.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.netty.buffer.ByteBuf;

public class RealTimeData {

	private CodeInfo codeInfo;

	private StockOtherData otherData;

	private ShareRealTimeData data;

	public RealTimeData(ByteBuf byteBuf) {
		super();
		this.codeInfo = new CodeInfo(byteBuf);
		this.otherData = new StockOtherData(byteBuf);

		if (makeSubMarket(codeInfo.getMarket()) == KIND_INDEX) {
			this.data = new IndexRealTime(byteBuf);
		} else if (makeMarket(codeInfo.getMarket()) == FUTURES_MARKET) {
			this.data = new FuturesRealTime(byteBuf);
		} else if (makeMarket(codeInfo.getMarket()) == HK_MARKET) {
			this.data = new HKStockRealTime(byteBuf);
		} else {
			this.data = new StockRealTime(byteBuf);
		}

	}

	public CodeInfo getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(CodeInfo codeInfo) {
		this.codeInfo = codeInfo;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
