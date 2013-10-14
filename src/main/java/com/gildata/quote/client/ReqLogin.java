package com.gildata.quote.client;

import static com.gildata.quote.client.QuoteConstants.GB18030;
import io.netty.buffer.ByteBuf;

public class ReqLogin extends Envelope {

	private String username = "";

	private String password = "";

	public ReqLogin() {
		super(EnvelopeType.RT_LOGIN);
	}

	public ReqLogin(EnvelopeType type) {
		super(type);
	}

	public ReqLogin(EnvelopeType type, String username, String password) {
		super(type);
		this.username = username;
		this.password = password;
	}

	public ReqLogin(String username, String password) {
		this(EnvelopeType.RT_LOGIN, username, password);
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
	public void encode(ByteBuf byteBuf) {

		byteBuf.writeShort(16);
		byteBuf.writeShort(0);

		QuoteUtils.writeString(byteBuf, username, 64, GB18030);
		QuoteUtils.writeString(byteBuf, password, 64, GB18030);

	}

}
