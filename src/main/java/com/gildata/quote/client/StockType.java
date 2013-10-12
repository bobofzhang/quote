package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.GB18030;
import static com.gildata.quote.client.QuoteConstants.STOCK_TYPE_NAME_SIZE;
import io.netty.buffer.ByteBuf;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StockType implements Encodable {

	private String stockTypeName;

	private short stockType; // 证券类型
	private short total; // 证券总数
	private short offset; // 偏移量
	private short priceUnit; // 价格单位
	private short totalTime; // 总开市时间（分钟）
	private short curTime;

	private Time[] times = new Time[12];

	public StockType(ByteBuf buf) {
		super();
		stockTypeName = buf.toString(buf.readerIndex(), STOCK_TYPE_NAME_SIZE,
				GB18030).trim();
		buf.skipBytes(STOCK_TYPE_NAME_SIZE);
		stockType = buf.readShort();
		total = buf.readShort();
		offset = buf.readShort();
		priceUnit = buf.readShort();
		totalTime = buf.readShort();
		curTime = buf.readShort();
		for (int i = 0; i < 12; i++) {
			times[i] = new Time(buf);
		}

	}

	public String getStockTypeName() {
		return stockTypeName;
	}

	public void setStockTypeName(String stockTypeName) {
		this.stockTypeName = stockTypeName;
	}

	public short getStockType() {
		return stockType;
	}

	public void setStockType(short stockType) {
		this.stockType = stockType;
	}

	public short getTotal() {
		return total;
	}

	public void setTotal(short total) {
		this.total = total;
	}

	public short getOffset() {
		return offset;
	}

	public void setOffset(short offset) {
		this.offset = offset;
	}

	public short getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(short priceUnit) {
		this.priceUnit = priceUnit;
	}

	public short getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(short totalTime) {
		this.totalTime = totalTime;
	}

	public short getCurTime() {
		return curTime;
	}

	public void setCurTime(short curTime) {
		this.curTime = curTime;
	}

	public Time[] getTimes() {
		return times;
	}

	public void setTimes(Time[] times) {
		this.times = times;
	}

	@Override
	public void encodeAsByteBuf(ByteBuf byteBuf) {
		QuoteUtils.writeString(byteBuf, stockTypeName, STOCK_TYPE_NAME_SIZE,
				GB18030);
		byteBuf.writeShort(total);
		byteBuf.writeShort(offset);
		byteBuf.writeShort(priceUnit);
		byteBuf.writeShort(totalTime);
		byteBuf.writeShort(curTime);
		for (Time t : times) {
			t.encodeAsByteBuf(byteBuf);
		}

	}

	@Override
	public String toString() {
//		return "StockType [name=" + stockTypeName + ", type="
//				+ stockType + ", total=" + total + ", offset=" + offset
//				+ ", priceUnit=" + priceUnit + ", totalTime=" + totalTime
//				+ ", curTime=" + curTime + ", times=" + Arrays.toString(times)
//				+ "]";
		
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		
	}

}
