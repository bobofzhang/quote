package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.GB18030;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.netty.buffer.ByteBuf;

public class AnsServerInfo extends Envelope {

	private String name;

	private String serialNo;

	private int totalCount;

	private int todayCount;

	private int nowCount;

	public AnsServerInfo() {
		super(EnvelopeType.RT_SERVERINFO);

	}

	public AnsServerInfo(ByteBuf byteBuf) {
		super(EnvelopeType.RT_SERVERINFO, byteBuf);
		this.name = QuoteUtils.readString(byteBuf, 32, GB18030);
		this.serialNo = QuoteUtils.readString(byteBuf, 12, GB18030);
		this.totalCount = byteBuf.readInt();
		this.todayCount = byteBuf.readInt();
		this.nowCount = byteBuf.readInt();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTodayCount() {
		return todayCount;
	}

	public void setTodayCount(int todayCount) {
		this.todayCount = todayCount;
	}

	public int getNowCount() {
		return nowCount;
	}

	public void setNowCount(int nowCount) {
		this.nowCount = nowCount;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
