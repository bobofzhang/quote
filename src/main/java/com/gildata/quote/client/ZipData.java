package com.gildata.quote.client;


public class ZipData {
	
	private long ziplength;
	private long originLength;
	private byte[] content;
	
	public long getZiplength() {
		return ziplength;
	}
	public void setZiplength(long ziplength) {
		this.ziplength = ziplength;
	}
	public long getOriginLength() {
		return originLength;
	}
	public void setOriginLength(long originLength) {
		this.originLength = originLength;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}

}
