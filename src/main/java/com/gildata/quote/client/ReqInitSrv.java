package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ReqInitSrv extends Envelope {
	
	private List<ServerCompare> serverCompares;

	public ReqInitSrv() {
		super(EnvelopeType.RT_INITIALINFO);
	}
	
	

	public List<ServerCompare> getServerCompares() {
		if(serverCompares ==null){
			serverCompares = new ArrayList<ServerCompare>();
		}
		return serverCompares;
	}



	public void setServerCompares(List<ServerCompare> serverCompares) {
		this.serverCompares = serverCompares;
	}



	@Override
	public void encode(ByteBuf byteBuf) {
		
		byteBuf.writeShort(getServerCompares().size()+1);
		byteBuf.writeShort(0);
		
		byteBuf.writeShort(getServerCompares().size());
		byteBuf.writeShort(0);
		for (ServerCompare sc:getServerCompares()){
			sc.encodeAsByteBuf(byteBuf);
		}
		
		

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	
}
