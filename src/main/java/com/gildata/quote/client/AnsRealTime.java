package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AnsRealTime extends Envelope {
	
	private short size;
	
	private List<RealTimeData> datas;
	
	public AnsRealTime() {
		super(EnvelopeType.RT_REALTIME);
	}
	
	public AnsRealTime(ByteBuf byteBuf) {
		super(EnvelopeType.RT_REALTIME, byteBuf);
		
	    size = byteBuf.readShort();
	    byteBuf.skipBytes(2);
		
	    datas = new ArrayList<RealTimeData>(size);

		for (int i = 0; i < size; i++) {
			datas.add(new RealTimeData(byteBuf));
		}

	}

	public short getSize() {
		return size;
	}

	public void setSize(short size) {
		this.size = size;
	}

	public List<RealTimeData> getDatas() {
		return datas;
	}

	public void setDatas(List<RealTimeData> datas) {
		this.datas = datas;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	

}
