package com.gildata.quote.client;

import java.nio.charset.Charset;

public final class QuoteConstants {

	public static final Charset GB18030 = Charset.forName("GB18030");

	public static final byte[] FLAG = { '2', '0', '0', '3' };

	public static final int STOCK_NAME_SIZE = 16;
	public static final int STOCK_TYPE_NAME_SIZE = 20;
	public static final int CODE_LEN = 6;
	public static final int HEADER_LEN = 16;
	
	public static final String EXCHANGE_SH_STOCK = "SH"; //上海股票交易所
	public static final String EXCHANGE_SZ_STOCK = "SZ"; //深圳股票交易所
	public static final String EXCHANGE_HK_STOCK = "HK"; //香港股票交易所
	public static final String EXCHANGE_DL_FUTURES = "DL"; //大连期货交易所
	public static final String EXCHANGE_SH_FUTURES = "SF"; //上海期货交易所
	public static final String EXCHANGE_ZZ_FUTURES = "ZZ"; //郑州期货交易所
	public static final String EXCHANGE_GD_FUTURES = "GD"; //黄金交易所
	public static final String EXCHANGE_IF_FUTURES = "IF"; //金融期货交易所

}
