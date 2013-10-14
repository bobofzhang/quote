package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.GB18030;
import static com.gildata.quote.client.QuoteConstants.STOCK_NAME_SIZE;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.netty.buffer.ByteBuf;

public class StockInitInfo implements Encodable {

	private String stockName;

	private CodeInfo stockCode;

	private int prevClose;

	private int fiveDayVol;

	public StockInitInfo(ByteBuf byteBuf) {
		super();
		this.stockName = QuoteUtils.readString(byteBuf, STOCK_NAME_SIZE,
				GB18030);
		this.stockCode = new CodeInfo(byteBuf);
		this.prevClose = byteBuf.readInt();
		this.fiveDayVol = byteBuf.readInt();

	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public CodeInfo getStockCode() {
		return stockCode;
	}

	public void setStockCode(CodeInfo stockCode) {
		this.stockCode = stockCode;
	}

	public int getPrevClose() {
		return prevClose;
	}

	public void setPrevClose(int prevClose) {
		this.prevClose = prevClose;
	}

	public int getFiveDayVol() {
		return fiveDayVol;
	}

	public void setFiveDayVol(int fiveDayVol) {
		this.fiveDayVol = fiveDayVol;
	}

	@Override
	public void encodeAsByteBuf(ByteBuf byteBuf) {

		QuoteUtils.writeString(byteBuf, stockName, STOCK_NAME_SIZE, GB18030);
		QuoteUtils.writeEncodable(byteBuf, stockCode, 8);
		byteBuf.writeInt(prevClose);
		byteBuf.writeInt(fiveDayVol);

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);

	}

}
