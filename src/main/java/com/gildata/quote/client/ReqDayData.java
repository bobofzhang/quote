package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ReqDayData extends Envelope {
	private short periodNum;
	private short size;
	private int beginPosition;
	private short day;
	private PeriodType period;
	private CodeInfo codeInfo;

	public ReqDayData() {
		super(EnvelopeType.RT_TECHDATA_EX);
	}

	public ReqDayData(EnvelopeType type) {
		super(type);
	}

	public short getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(short periodNum) {
		this.periodNum = periodNum;
	}

	public short getSize() {
		return size;
	}

	public void setSize(short size) {
		this.size = size;
	}

	public int getBeginPosition() {
		return beginPosition;
	}

	public void setBeginPosition(int beginPosition) {
		this.beginPosition = beginPosition;
	}

	public short getDay() {
		return day;
	}

	public void setDay(short day) {
		this.day = day;
	}

	public PeriodType getPeriod() {
		return period;
	}

	public void setPeriod(PeriodType period) {
		this.period = period;
	}

	public CodeInfo getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(CodeInfo codeInfo) {
		this.codeInfo = codeInfo;
	}
	
	

	@Override
	public void encode(ByteBuf byteBuf) {
		
		byteBuf.writeShort(-20);
		byteBuf.writeShort(0);
		
		byteBuf.writeShort(periodNum);
		byteBuf.writeShort(size);
		byteBuf.writeInt(beginPosition);
		byteBuf.writeShort(day);
		byteBuf.writeShort(period.getValue());
		QuoteUtils.writeEncodable(byteBuf, codeInfo, 8);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
