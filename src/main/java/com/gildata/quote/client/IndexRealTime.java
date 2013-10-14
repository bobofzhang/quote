package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class IndexRealTime implements ShareRealTimeData {

	private int open; // 今开盘
	private int high; // 最高价
	private int low; // 最低价
	private int price; // 最新价
	private int vol; // 成交量
	private float avg; // 成交金额

	private short advance; // 上涨家数
	private short decline; // 下跌家数

	private int samples; // 对于综合指数：所有股票 - 指数 对于分类指数：本类股票总数

	private int bidSize; // 委买数
	private int askSize; // 委卖数

	private short type; // 指数种类：0-综合指数 1-A股 2-B股
	private short lead; // 领先指标
	private short riseTrend; // 上涨趋势
	private short fallTrend; // 下跌趋势
	private short samples2; // 对于综合指数：A股 + B股 对于分类指数：0
	private int adl; // ADL 指标
	private int volUnit; // 每手股数

	public IndexRealTime(ByteBuf byteBuf) {
		super();
		this.open = byteBuf.readInt();
		this.high = byteBuf.readInt();
		this.low = byteBuf.readInt();
		this.price = byteBuf.readInt();
		this.advance = byteBuf.readShort();
		this.decline = byteBuf.readShort();

		this.samples = byteBuf.readInt();
		this.bidSize = byteBuf.readInt();
		this.askSize = byteBuf.readInt();

		this.type = byteBuf.readShort();
		this.lead = byteBuf.readShort();
		this.riseTrend = byteBuf.readShort();
		this.fallTrend = byteBuf.readShort();
		byteBuf.skipBytes(10);
		this.samples2 = byteBuf.readShort();
		this.adl = byteBuf.readInt();
		byteBuf.skipBytes(12);
		this.volUnit = byteBuf.readInt();
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}

	public float getAvg() {
		return avg;
	}

	public void setAvg(float avg) {
		this.avg = avg;
	}

	public short getAdvance() {
		return advance;
	}

	public void setAdvance(short advance) {
		this.advance = advance;
	}

	public short getDecline() {
		return decline;
	}

	public void setDecline(short decline) {
		this.decline = decline;
	}

	public int getSamples() {
		return samples;
	}

	public void setSamples(int samples) {
		this.samples = samples;
	}

	public int getBidSize() {
		return bidSize;
	}

	public void setBidSize(int bidSize) {
		this.bidSize = bidSize;
	}

	public int getAskSize() {
		return askSize;
	}

	public void setAskSize(int askSize) {
		this.askSize = askSize;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public short getLead() {
		return lead;
	}

	public void setLead(short lead) {
		this.lead = lead;
	}

	public short getRiseTrend() {
		return riseTrend;
	}

	public void setRiseTrend(short riseTrend) {
		this.riseTrend = riseTrend;
	}

	public short getFallTrend() {
		return fallTrend;
	}

	public void setFallTrend(short fallTrend) {
		this.fallTrend = fallTrend;
	}

	public short getSamples2() {
		return samples2;
	}

	public void setSamples2(short samples2) {
		this.samples2 = samples2;
	}

	public int getAdl() {
		return adl;
	}

	public void setAdl(int adl) {
		this.adl = adl;
	}

	public int getVolUnit() {
		return volUnit;
	}

	public void setVolUnit(int volUnit) {
		this.volUnit = volUnit;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
