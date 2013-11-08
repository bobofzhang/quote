package com.gildata.quote.client;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

public class ServerList {

	private List<InetSocketAddress> addresses;
	
	private AtomicInteger index  = new AtomicInteger(0);

	public ServerList(String config) {
		super();

		String[] srvs = StringUtils.split(config, '|');
		addresses = new ArrayList<InetSocketAddress>(srvs.length);
		for (String srv : srvs) {
			InetSocketAddress addr = new InetSocketAddress(
					StringUtils.substringBefore(srv, ":"),
					Integer.parseInt(StringUtils.substringAfter(srv, ":")));
			addresses.add(addr);
		}
	}
	
	public List<InetSocketAddress> getAddresses() {
		return addresses;
	}



	public void setAddresses(List<InetSocketAddress> addresses) {
		this.addresses = addresses;
	}


	public InetSocketAddress getAddress(){
		
		int i = index.get();
		if ( i < addresses.size() -1){
			return addresses.get(index.getAndIncrement());
		}else{
			return addresses.get(index.getAndSet(0));

		}
	}
}
