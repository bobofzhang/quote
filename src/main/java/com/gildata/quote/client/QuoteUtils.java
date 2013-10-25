package com.gildata.quote.client;

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

}
