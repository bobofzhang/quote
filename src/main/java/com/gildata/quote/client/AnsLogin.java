package com.gildata.quote.client;

import io.netty.buffer.ByteBuf;

public class AnsLogin extends Envelope {

	public AnsLogin() {
		super(EnvelopeType.RT_LOGIN);
	}

	public AnsLogin(ByteBuf buf) {
		super(EnvelopeType.RT_LOGIN, buf);

	}

}
