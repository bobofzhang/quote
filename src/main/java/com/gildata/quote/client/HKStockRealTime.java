package com.gildata.quote.client;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.netty.buffer.ByteBuf;

public class HKStockRealTime implements ShareRealTimeData {

	private int open; // 今开盘
	private int high; // 最高价
	private int low; // 最低价
	private int price; // 最新价
	private int vol; // 成交量(单位:股)
	private float avg; // 成交金额
	private int yield; // 周息率 股票相关 溢价% 认股证相关
						// 认购证的溢价＝（认股证现价×兑换比率＋行使价－相关资产现价）/相关资产现价×100
						// 认沽证的溢价＝（认股证现价×兑换比率－行使价＋相关资产现价）/相关资产现价×100
	private int bid; // 买价
	private int ask; // 买价

	private int bidSize1; // 买一量
	private int bidSize2; // 买二量
	private int bidSize3; // 买三量
	private int bidSize4; // 买四量
	private int bidSize5; // 买五量

	private int askSize1; // 卖一量
	private int askSize2; // 卖二量
	private int askSize3; // 卖三量
	private int askSize4; // 卖四量
	private int askSize5; // 卖五量

	private short bidOrder1; // 买一盘数
	private short bidOrder2; // 买二盘数
	private short bidOrder3; // 买三盘数
	private short bidOrder4; // 买四盘数
	private short bidOrder5; // 买五盘数

	private short askOrder1; // 卖一盘数
	private short askOrder2; // 卖二盘数
	private short askOrder3; // 卖三盘数
	private short askOrder4; // 卖四盘数
	private short askOrder5; // 卖五盘数

	private int IEP; // 参考平衡价
	private int IEV; // 参考平衡量
	private int matchType; // 对盘分类

	public HKStockRealTime(ByteBuf byteBuf) {
		super();
		this.open = byteBuf.readInt();
		this.high = byteBuf.readInt();
		this.low = byteBuf.readInt();
		this.price = byteBuf.readInt();
		this.vol = byteBuf.readInt();
		this.avg = byteBuf.readFloat();
		this.yield = byteBuf.readInt();
		this.bid = byteBuf.readInt();
		this.ask = byteBuf.readInt();

		this.bidSize1 = byteBuf.readInt();
		this.bidSize2 = byteBuf.readInt();
		this.bidSize3 = byteBuf.readInt();
		this.bidSize4 = byteBuf.readInt();
		this.bidSize5 = byteBuf.readInt();

		this.askSize1 = byteBuf.readInt();
		this.askSize2 = byteBuf.readInt();
		this.askSize3 = byteBuf.readInt();
		this.askSize4 = byteBuf.readInt();
		this.askSize5 = byteBuf.readInt();

		this.bidOrder1 = byteBuf.readShort();
		this.bidOrder2 = byteBuf.readShort();
		this.bidOrder3 = byteBuf.readShort();
		this.bidOrder4 = byteBuf.readShort();
		this.bidOrder5 = byteBuf.readShort();

		this.askOrder1 = byteBuf.readShort();
		this.askOrder2 = byteBuf.readShort();
		this.askOrder3 = byteBuf.readShort();
		this.askOrder4 = byteBuf.readShort();
		this.askOrder5 = byteBuf.readShort();

		this.IEP = byteBuf.readInt();
		this.IEV = byteBuf.readInt();
		this.matchType = byteBuf.readInt();
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

	public int getYield() {
		return yield;
	}

	public void setYield(int yield) {
		this.yield = yield;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getAsk() {
		return ask;
	}

	public void setAsk(int ask) {
		this.ask = ask;
	}

	public int getBidSize1() {
		return bidSize1;
	}

	public void setBidSize1(int bidSize1) {
		this.bidSize1 = bidSize1;
	}

	public int getBidSize2() {
		return bidSize2;
	}

	public void setBidSize2(int bidSize2) {
		this.bidSize2 = bidSize2;
	}

	public int getBidSize3() {
		return bidSize3;
	}

	public void setBidSize3(int bidSize3) {
		this.bidSize3 = bidSize3;
	}

	public int getBidSize4() {
		return bidSize4;
	}

	public void setBidSize4(int bidSize4) {
		this.bidSize4 = bidSize4;
	}

	public int getBidSize5() {
		return bidSize5;
	}

	public void setBidSize5(int bidSize5) {
		this.bidSize5 = bidSize5;
	}

	public int getAskSize1() {
		return askSize1;
	}

	public void setAskSize1(int askSize1) {
		this.askSize1 = askSize1;
	}

	public int getAskSize2() {
		return askSize2;
	}

	public void setAskSize2(int askSize2) {
		this.askSize2 = askSize2;
	}

	public int getAskSize3() {
		return askSize3;
	}

	public void setAskSize3(int askSize3) {
		this.askSize3 = askSize3;
	}

	public int getAskSize4() {
		return askSize4;
	}

	public void setAskSize4(int askSize4) {
		this.askSize4 = askSize4;
	}

	public int getAskSize5() {
		return askSize5;
	}

	public void setAskSize5(int askSize5) {
		this.askSize5 = askSize5;
	}

	public short getBidOrder1() {
		return bidOrder1;
	}

	public void setBidOrder1(short bidOrder1) {
		this.bidOrder1 = bidOrder1;
	}

	public short getBidOrder2() {
		return bidOrder2;
	}

	public void setBidOrder2(short bidOrder2) {
		this.bidOrder2 = bidOrder2;
	}

	public short getBidOrder3() {
		return bidOrder3;
	}

	public void setBidOrder3(short bidOrder3) {
		this.bidOrder3 = bidOrder3;
	}

	public short getBidOrder4() {
		return bidOrder4;
	}

	public void setBidOrder4(short bidOrder4) {
		this.bidOrder4 = bidOrder4;
	}

	public short getBidOrder5() {
		return bidOrder5;
	}

	public void setBidOrder5(short bidOrder5) {
		this.bidOrder5 = bidOrder5;
	}

	public short getAskOrder1() {
		return askOrder1;
	}

	public void setAskOrder1(short askOrder1) {
		this.askOrder1 = askOrder1;
	}

	public short getAskOrder2() {
		return askOrder2;
	}

	public void setAskOrder2(short askOrder2) {
		this.askOrder2 = askOrder2;
	}

	public short getAskOrder3() {
		return askOrder3;
	}

	public void setAskOrder3(short askOrder3) {
		this.askOrder3 = askOrder3;
	}

	public short getAskOrder4() {
		return askOrder4;
	}

	public void setAskOrder4(short askOrder4) {
		this.askOrder4 = askOrder4;
	}

	public short getAskOrder5() {
		return askOrder5;
	}

	public void setAskOrder5(short askOrder5) {
		this.askOrder5 = askOrder5;
	}

	public int getIEP() {
		return IEP;
	}

	public void setIEP(int iEP) {
		IEP = iEP;
	}

	public int getIEV() {
		return IEV;
	}

	public void setIEV(int iEV) {
		IEV = iEV;
	}

	public int getMatchType() {
		return matchType;
	}

	public void setMatchType(int matchType) {
		this.matchType = matchType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);

	}

}
