package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.GB18030;
import static com.gildata.quote.client.QuoteConstants.STOCK_TYPE_NAME_SIZE;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CommBourseInfo implements Encodable {

	private String typeName;
	private short marketType;
	private short count;
	private int date;
	private int crc;
	private List<StockType> newTypes;

	public CommBourseInfo(ByteBuf byteBuf) {
		super();
		typeName = QuoteUtils.readString(byteBuf, STOCK_TYPE_NAME_SIZE, GB18030);
		marketType = byteBuf.readShort();
		count = byteBuf.readShort();
		date = byteBuf.readInt();
		crc = byteBuf.readInt();

		newTypes = new ArrayList<StockType>(count);

		for (int i = 0; i < count; i++) {
			newTypes.add(new StockType(byteBuf));
		}

	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public short getMarketType() {
		return marketType;
	}

	public void setMarketType(short marketType) {
		this.marketType = marketType;
	}

	public short getCount() {
		return count;
	}

	public void setCount(short count) {
		this.count = count;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getCrc() {
		return crc;
	}

	public void setCrc(int crc) {
		this.crc = crc;
	}

	public List<StockType> getNewTypes() {
		
		if (newTypes  == null){
			newTypes = new ArrayList<StockType>();
		}
		return newTypes;
	}

	public void setNewTypes(List<StockType> newTypes) {
		this.newTypes = newTypes;
	}

	@Override
	public void encodeAsByteBuf(ByteBuf byteBuf) {
		QuoteUtils
				.writeString(byteBuf, typeName, STOCK_TYPE_NAME_SIZE, GB18030);
		byteBuf.writeShort(marketType);
		byteBuf.writeShort(count);
		byteBuf.writeInt(date);
		byteBuf.writeInt(crc);
		for (StockType type : getNewTypes()) {
			type.encodeAsByteBuf(byteBuf);
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
