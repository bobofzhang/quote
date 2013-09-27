package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

public class ReqLogin extends Envelope{
	
	private String username;
	
	private String password;

	public ReqLogin() {
		super(EnvelopeType.RT_LOGIN);
	}
	
	

	public ReqLogin(String username, String password) {
		super(EnvelopeType.RT_LOGIN);
		this.username = username;
		this.password = password;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public int getBodyLength() {
		return 132;
	}

	@Override
	public void encodeBody(ByteBuf byteBuf) {
		byteBuf.writeShort(-128);
		byteBuf.writeShort(0);
		if (username != null) {
			byte[] b = username.getBytes();
			int len = b.length;
			if (len > 64) {
				byteBuf.writeBytes(b, 0, 64);
			} else {
				byteBuf.writeBytes(b);
				if (len < 64) {
					byteBuf.writeZero(64 - len);
				}
			}
		} else {
			byteBuf.writeZero(64);
		}
		
		if (password != null) {
			byte[] b = password.getBytes();
			int len = b.length;
			if (len > 64) {
				byteBuf.writeBytes(b, 0, 64);
			} else {
				byteBuf.writeBytes(b);
				if (len < 64) {
					byteBuf.writeZero(64 - len);
				}
			}
		} else {
			byteBuf.writeZero(64);
		}
	}

}
