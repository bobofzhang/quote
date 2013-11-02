package com.gildata.quote.client;

import static com.gildata.quote.client.MarketType.DALIAN_BOURSE;
import static com.gildata.quote.client.MarketType.FUTURES_MARKET;
import static com.gildata.quote.client.MarketType.GE_BOURSE;
import static com.gildata.quote.client.MarketType.GUZHI_BOURSE;
import static com.gildata.quote.client.MarketType.HK_BOURSE;
import static com.gildata.quote.client.MarketType.HK_MARKET;
import static com.gildata.quote.client.MarketType.HUANGJIN_BOURSE;
import static com.gildata.quote.client.MarketType.INDEX_BOURSE;
import static com.gildata.quote.client.MarketType.SHANGHAI_BOURSE;
import static com.gildata.quote.client.MarketType.SH_BOURSE;
import static com.gildata.quote.client.MarketType.STOCK_MARKET;
import static com.gildata.quote.client.MarketType.SZ_BOURSE;
import static com.gildata.quote.client.MarketType.ZHENGZHOU_BOURSE;
import static com.gildata.quote.client.MarketType.isMarketBourse;
import static com.gildata.quote.client.QuoteConstants.EXCHANGE_DL_FUTURES;
import static com.gildata.quote.client.QuoteConstants.EXCHANGE_GD_FUTURES;
import static com.gildata.quote.client.QuoteConstants.EXCHANGE_HK_STOCK;
import static com.gildata.quote.client.QuoteConstants.EXCHANGE_IF_FUTURES;
import static com.gildata.quote.client.QuoteConstants.EXCHANGE_SH_FUTURES;
import static com.gildata.quote.client.QuoteConstants.EXCHANGE_SH_STOCK;
import static com.gildata.quote.client.QuoteConstants.EXCHANGE_SZ_STOCK;
import static com.gildata.quote.client.QuoteConstants.EXCHANGE_ZZ_FUTURES;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

import net.sourceforge.pinyin4j.PinyinHelper;

public class QuoteUtils {

	public static String readString(ByteBuf byteBuf, int length, Charset charset) {
		String ret = byteBuf.toString(byteBuf.readerIndex(), length, charset)
				.trim();
		byteBuf.skipBytes(length);
		return ret;

	}

	public static void writeString(ByteBuf byteBuf, String str, int length,
			Charset charset) {
		if (str != null) {
			byte[] b = str.getBytes(charset);
			int len = b.length;
			if (len > length) {
				byteBuf.writeBytes(b, 0, length);
			} else {
				byteBuf.writeBytes(b);
				if (len < length) {
					byteBuf.writeZero(length - len);
				}
			}
		} else {
			byteBuf.writeZero(length);
		}
	}

	public static void writeEncodable(ByteBuf byteBuf, Encodable encodable,
			int length) {
		if (encodable == null) {
			byteBuf.writeZero(length);
		} else {
			encodable.encodeAsByteBuf(byteBuf);
		}

	}

	public static String toPingYinFirstChar(String str) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < str.length(); ++i)

		{

			String[] py = PinyinHelper.toHanyuPinyinStringArray(str.charAt(i));
			if (py == null) {
				sb.append(str.charAt(i));
			} else {
				sb.append(py[0].charAt(0));

			}

		}
		return sb.toString().toUpperCase();
	}

	public static String toMarketCode(short marketType) {
		if (isMarketBourse(marketType, STOCK_MARKET, SH_BOURSE)) {
			return EXCHANGE_SH_STOCK;
		} else if (isMarketBourse(marketType, STOCK_MARKET, SZ_BOURSE)) {
			return EXCHANGE_SZ_STOCK;
		} else if (isMarketBourse(marketType, FUTURES_MARKET, DALIAN_BOURSE)) {
			return EXCHANGE_DL_FUTURES;
		} else if (isMarketBourse(marketType, FUTURES_MARKET, SHANGHAI_BOURSE)) {
			return EXCHANGE_SH_FUTURES;
		} else if (isMarketBourse(marketType, FUTURES_MARKET, ZHENGZHOU_BOURSE)) {
			return EXCHANGE_ZZ_FUTURES;
		} else if (isMarketBourse(marketType, FUTURES_MARKET, HUANGJIN_BOURSE)) {
			return EXCHANGE_GD_FUTURES;
		} else if (isMarketBourse(marketType, FUTURES_MARKET, GUZHI_BOURSE)) {
			return EXCHANGE_IF_FUTURES;
		} else if (isMarketBourse(marketType, HK_MARKET, HK_BOURSE)) {
			return EXCHANGE_HK_STOCK;
		} else if (isMarketBourse(marketType, HK_MARKET, GE_BOURSE)) {
			return EXCHANGE_HK_STOCK;
		} else if (isMarketBourse(marketType, HK_MARKET, INDEX_BOURSE)) {
			return EXCHANGE_HK_STOCK;
		} else {
			return null;
		}
		
	};

}
