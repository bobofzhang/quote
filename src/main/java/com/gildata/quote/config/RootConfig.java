package com.gildata.quote.config;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.gildata.quote.client.QuoteClientInitializer;

@Configuration
@ComponentScan(basePackages = { "com.gildata.quote.client" })
@PropertySource("classpath:netty.properties")
public class RootConfig {
	
//	@Autowired 
//	Environment env;
	
	@Value("${host}")
	private String host;
	
	@Value("${port}")
	private int port;
	
	@Autowired
	private QuoteClientInitializer quoteClientInitializer;
	
	@Bean(destroyMethod = "shutdownGracefully")
	@Qualifier("workerGroup")
	public NioEventLoopGroup workerGroup() {
	    return new NioEventLoopGroup();
	}
	
	@Bean
	@Qualifier("bootstrap")
	public Bootstrap bootstrap() {
	    Bootstrap b = new Bootstrap();
	    b.group(workerGroup())
	        .channel(NioSocketChannel.class)
	        .handler(quoteClientInitializer);
	    return b;
	}
	
	@Bean
	@Qualifier("address")
	public InetSocketAddress address() {
		return new InetSocketAddress(host,port);
	}
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }

	

}
