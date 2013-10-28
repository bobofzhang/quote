package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StockRealTime implements ShareRealTimeData {

	private int open; // 今开盘
	private int high; // 最高价
	private int low; // 最低价
	private int price; // 最新价
	private int vol; // 成交量(单位:股)
	private float amount; // 成交金额

	private int bid1; // 买一价
	private int bidSize1; // 买一量
	private int bid2; // 买二价
	private int bidSize2; // 买二量
	private int bid3; // 买三价
	private int bidSize3; // 买三量
	private int bid4; // 买四价
	private int bidSize4; // 买四量
	private int bid5; // 买五价
	private int bidSize5; // 买五量

	private int ask1; // 卖一价
	private int askSize1; // 卖一量
	private int ask2; // 卖二价
	private int askSize2; // 卖二量
	private int ask3; // 卖三价
	private int askSize3; // 卖三量
	private int ask4; // 卖四价
	private int askSize4; // 卖四量
	private int ask5; // 卖五价
	private int askSize5; // 卖五量

	private int volUnit; // 每手股数 (是否可放入代码表中？？？？）
	private int nationalDebtRatio; // 国债利率,基金净值

	public StockRealTime(ByteBuf byteBuf) {
		super();
		this.open = byteBuf.readInt();
		this.high = byteBuf.readInt();
		this.low = byteBuf.readInt();
		this.price = byteBuf.readInt();
		this.vol = byteBuf.readInt();
		this.amount = byteBuf.readFloat();

		this.bid1 = byteBuf.readInt();
		this.bidSize1 = byteBuf.readInt();
		this.bid2 = byteBuf.readInt();
		this.bidSize2 = byteBuf.readInt();
		this.bid3 = byteBuf.readInt();
		this.bidSize3 = byteBuf.readInt();
		this.bid4 = byteBuf.readInt();
		this.bidSize4 = byteBuf.readInt();
		this.bid5 = byteBuf.readInt();
		this.bidSize5 = byteBuf.readInt();

		this.ask1 = byteBuf.readInt();
		this.askSize1 = byteBuf.readInt();
		this.ask2 = byteBuf.readInt();
		this.askSize2 = byteBuf.readInt();
		this.ask3 = byteBuf.readInt();
		this.askSize3 = byteBuf.readInt();
		this.ask4 = byteBuf.readInt();
		this.askSize4 = byteBuf.readInt();
		this.ask5 = byteBuf.readInt();
		this.askSize5 = byteBuf.readInt();

		this.volUnit = byteBuf.readInt();
		this.nationalDebtRatio = byteBuf.readInt();
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getBid1() {
		return bid1;
	}

	public void setBid1(int bid1) {
		this.bid1 = bid1;
	}

	public int getBidSize1() {
		return bidSize1;
	}

	public void setBidSize1(int bidSize1) {
		this.bidSize1 = bidSize1;
	}

	public int getBid2() {
		return bid2;
	}

	public void setBid2(int bid2) {
		this.bid2 = bid2;
	}

	public int getBidSize2() {
		return bidSize2;
	}

	public void setBidSize2(int bidSize2) {
		this.bidSize2 = bidSize2;
	}

	public int getBid3() {
		return bid3;
	}

	public void setBid3(int bid3) {
		this.bid3 = bid3;
	}

	public int getBidSize3() {
		return bidSize3;
	}

	public void setBidSize3(int bidSize3) {
		this.bidSize3 = bidSize3;
	}

	public int getBid4() {
		return bid4;
	}

	public void setBid4(int bid4) {
		this.bid4 = bid4;
	}

	public int getBidSize4() {
		return bidSize4;
	}

	public void setBidSize4(int bidSize4) {
		this.bidSize4 = bidSize4;
	}

	public int getBid5() {
		return bid5;
	}

	public void setBid5(int bid5) {
		this.bid5 = bid5;
	}

	public int getBidSize5() {
		return bidSize5;
	}

	public void setBidSize5(int bidSize5) {
		this.bidSize5 = bidSize5;
	}

	public int getAsk1() {
		return ask1;
	}

	public void setAsk1(int ask1) {
		this.ask1 = ask1;
	}

	public int getAskSize1() {
		return askSize1;
	}

	public void setAskSize1(int askSize1) {
		this.askSize1 = askSize1;
	}

	public int getAsk2() {
		return ask2;
	}

	public void setAsk2(int ask2) {
		this.ask2 = ask2;
	}

	public int getAskSize2() {
		return askSize2;
	}

	public void setAskSize2(int askSize2) {
		this.askSize2 = askSize2;
	}

	public int getAsk3() {
		return ask3;
	}

	public void setAsk3(int ask3) {
		this.ask3 = ask3;
	}

	public int getAskSize3() {
		return askSize3;
	}

	public void setAskSize3(int askSize3) {
		this.askSize3 = askSize3;
	}

	public int getAsk4() {
		return ask4;
	}

	public void setAsk4(int ask4) {
		this.ask4 = ask4;
	}

	public int getAskSize4() {
		return askSize4;
	}

	public void setAskSize4(int askSize4) {
		this.askSize4 = askSize4;
	}

	public int getAsk5() {
		return ask5;
	}

	public void setAsk5(int ask5) {
		this.ask5 = ask5;
	}

	public int getAskSize5() {
		return askSize5;
	}

	public void setAskSize5(int askSize5) {
		this.askSize5 = askSize5;
	}

	public int getVolUnit() {
		return volUnit;
	}

	public void setVolUnit(int volUnit) {
		this.volUnit = volUnit;
	}

	public int getNationalDebtRatio() {
		return nationalDebtRatio;
	}

	public void setNationalDebtRatio(int nationalDebtRatio) {
		this.nationalDebtRatio = nationalDebtRatio;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
