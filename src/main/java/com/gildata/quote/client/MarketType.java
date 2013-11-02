package com.gildata.quote.client;

public class MarketType {

	public static final short STOCK_MARKET = (short) 0x1000;// 股票
	public static final short SH_BOURSE = (short) 0x0100;// 上海
	public static final short SZ_BOURSE = (short) 0x0200;// 深圳
	public static final short BK_BOURES = (short) 0x0300;// 板块
	public static final short SYSBK_BOURSE = (short) 0x0400;// 系统板块
	public static final short USERDEF_BOURSE = (short) 0x0800;// 自定义（自选股或者自定义板块）
	public static final short KIND_INDEX = (short) 0x0000;// 指数
	public static final short KIND_STOCKA = (short) 0x0001;// A股
	public static final short KIND_STOCKB = (short) 0x0002;// B股
	public static final short KIND_BOND = (short) 0x0003;// 债券
	public static final short KIND_FUND = (short) 0x0004;// 基金
	public static final short KIND_THREEBOAD = (short) 0x0005;// 三板
	public static final short KIND_SMALLSTOCK = (short) 0x0006;// 中小盘股
	public static final short KIND_PLACE = (short) 0x0007;// 配售
	public static final short KIND_LOF = (short) 0x0008;// LOF
	public static final short KIND_ETF = (short) 0x0009;// ETF
	public static final short KIND_QuanZhen = (short) 0x000A;// 权证
	public static final short KIND_VENTURE = (short) 0x000D;// 创业板

	public static final short KIND_OtherIndex = (short) 0x000E;// 第三方行情分类，如:中信指数

	public static final short SC_Others = (short) 0x000F;// 其他 =(short)0x09
	public static final short KIND_USERDEFINE = (short) 0x0010;// 自定义指数

	// 港股市场
	public static final short HK_MARKET = (short) 0x2000;// 港股分类
	public static final short HK_BOURSE = (short) 0x0100;// 主板市场
	public static final short GE_BOURSE = (short) 0x0200;// 创业板市场(Growth
															// Enterprise
	// Market)
	public static final short INDEX_BOURSE = (short) 0x0300;// 指数市场
	public static final short HK_KIND_INDEX = (short) 0x0000;// 港指
	public static final short HK_KIND_FUTURES_INDEX = (short) 0x0001;// 期指
	// public static final short KIND_Option =(short)0x0002;// 港股期权

	public static final short HK_KIND_RES = (short) 0x0000;// 预留
	public static final short HK_KIND_BOND = (short) 0x0001;// BOND-债券
	public static final short HK_KIND_BWRT = (short) 0x0002;// BWRT-一篮子衍生权证
	public static final short HK_KIND_EQTY = (short) 0x0003;// EQTY-公司股票
	public static final short HK_KIND_TRST = (short) 0x0004;// TRST-信托
	public static final short HK_KIND_WRNT = (short) 0x0005;// WRNT-权证
	public static final short KIND_ETS = (short) 0x0006;// ETS
	public static final short KIND_NADS = (short) 0x0007;// NADS
	public static final short KIND_LY = (short) 0x0008;// 旅游
	public static final short KIND_GY = (short) 0x0009;// 工业
	public static final short KIND_GG = (short) 0x000A;// 公用
	public static final short KIND_QT = (short) 0x000F;// 其它

	/* 期货大类 */
	public static final short FUTURES_MARKET = (short) 0x4000;// 期货
	public static final short DALIAN_BOURSE = (short) 0x0100;// 大连
	public static final short KIND_BEAN = (short) 0x0001;// 连豆
	public static final short KIND_YUMI = (short) 0x0002;// 大连玉米
	public static final short KIND_SHIT = (short) 0x0003;// 大宗食糖
	public static final short KIND_DZGY = (short) 0x0004;// 大宗工业1
	public static final short KIND_DZGY2 = (short) 0x0005;// 大宗工业2
	public static final short KIND_DOUYOU = (short) 0x0006;// 连豆油
	public static final short KIND_JYX = (short) 0x0007;// 聚乙烯
	public static final short KIND_ZTY = (short) 0x0008;// 棕榈油

	public static final short SHANGHAI_BOURSE = (short) 0x0200;// 上海
	public static final short KIND_METAL = (short) 0x0001;// 上海金属
	public static final short KIND_RUBBER = (short) 0x0002;// 上海橡胶
	public static final short KIND_FUEL = (short) 0x0003;// 上海燃油
	// public static final short KIND_GUZHI =(short)0x0004 ;// 股指期货
	public static final short KIND_QHGOLD = (short) 0x0005;// 上海黄金

	public static final short ZHENGZHOU_BOURSE = (short) 0x0300;// 郑州
	public static final short KIND_XIAOM = (short) 0x0001;// 郑州小麦
	public static final short KIND_MIANH = (short) 0x0002;// 郑州棉花
	public static final short KIND_BAITANG = (short) 0x0003;// 郑州白糖
	public static final short KIND_PTA = (short) 0x0004;// 郑州PTA
	public static final short KIND_CZY = (short) 0x0005;// 菜籽油

	public static final short HUANGJIN_BOURSE = (short) 0x0400;// 黄金交易所
	public static final short KIND_GOLD = (short) 0x0001;// 上海黄金

	public static final short GUZHI_BOURSE = (short) 0x0500;// 股指期货
	public static final short KIND_GUZHI = (short) 0x0001;// 股指期货

	/* 外盘大类 */
	public static final short WP_MARKET = (short) 0x5000;// 外盘
	public static final short WP_INDEX = (short) 0x0100;// 国际指数 ;// 不用了
	public static final short WP_LME = (short) 0x0200;// LME ;// 不用了
	public static final short WP_LME_CLT = (short) 0x0210;// "场内铜";
	public static final short WP_LME_CLL = (short) 0x0220;// "场内铝";
	public static final short WP_LME_CLM = (short) 0x0230;// "场内镍";
	public static final short WP_LME_CLQ = (short) 0x0240;// "场内铅";
	public static final short WP_LME_CLX = (short) 0x0250;// "场内锌";
	public static final short WP_LME_CWT = (short) 0x0260;// "场外铝";
	public static final short WP_LME_CW = (short) 0x0270;// "场外";
	public static final short WP_LME_SUB = (short) 0x0000;

	public static final short WP_CBOT = (short) 0x0300;// CBOT
	public static final short WP_NYMEX = (short) 0x0400;// NYMEX
	public static final short WP_NYMEX_YY = (short) 0x0000;// "原油";
	public static final short WP_NYMEX_RY = (short) 0x0001;// "燃油";
	public static final short WP_NYMEX_QY = (short) 0x0002;// "汽油";

	public static final short WP_COMEX = (short) 0x0500;// COMEX
	public static final short WP_TOCOM = (short) 0x0600;// TOCOM
	public static final short WP_IPE = (short) 0x0700;// IPE
	public static final short WP_NYBOT = (short) 0x0800;// NYBOT
	public static final short WP_NOBLE_METAL = (short) 0x0900;// 贵金属
	public static final short WP_NOBLE_METAL_XH = (short) 0x0000;// "现货";
	public static final short WP_NOBLE_METAL_HJ = (short) 0x0001;// "黄金";
	public static final short WP_NOBLE_METAL_BY = (short) 0x0002;// "白银";

	public static final short WP_FUTURES_INDEX = (short) 0x0a00;// 期指
	public static final short WP_SICOM = (short) 0x0b00;// SICOM
	public static final short WP_LIBOR = (short) 0x0c00;// LIBOR
	public static final short WP_NYSE = (short) 0x0d00;// NYSE
	public static final short WP_CEC = (short) 0x0e00;// CEC

	public static final short WP_INDEX_AZ = (short) 0x0110;// "澳洲";
	public static final short WP_INDEX_OZ = (short) 0x0120;// "欧洲";
	public static final short WP_INDEX_MZ = (short) 0x0130;// "美洲";
	public static final short WP_INDEX_TG = (short) 0x0140;// "泰国";
	public static final short WP_INDEX_YL = (short) 0x0150;// "印尼";
	public static final short WP_INDEX_RH = (short) 0x0160;// "日韩";
	public static final short WP_INDEX_XHP = (short) 0x0170;// "新加坡";
	public static final short WP_INDEX_FLB = (short) 0x0180;// "菲律宾";
	public static final short WP_INDEX_CCN = (short) 0x0190;// "中国大陆";
	public static final short WP_INDEX_TW = (short) 0x01a0;// "中国台湾";
	public static final short WP_INDEX_MLX = (short) 0x01b0;// "马来西亚";
	public static final short WP_INDEX_SUB = (short) 0x0000;

	/* 外汇大类 */
	public static final short FOREIGN_MARKET = (short) 0x8000;// 外汇
	public static final short WH_BASE_RATE = (short) 0x0100;// 基本汇率
	public static final short WH_ACROSS_RATE = (short) 0x0200;// 交叉汇率
	public static final short FX_TYPE_AU = (short) 0x0000;// AU 澳元
	public static final short FX_TYPE_CA = (short) 0x0001;// CA 加元
	public static final short FX_TYPE_CN = (short) 0x0002;// CN 人民币
	public static final short FX_TYPE_DM = (short) 0x0003;// DM 马克
	public static final short FX_TYPE_ER = (short) 0x0004;// ER 欧元
	public static final short FX_TYPE_HK = (short) 0x0005;// HK 港币
	public static final short FX_TYPE_SF = (short) 0x0006;// SF 瑞士
	public static final short FX_TYPE_UK = (short) 0x0007;// UK 英镑
	public static final short FX_TYPE_YN = (short) 0x0008;// YN 日元

	public static final short WH_FUTURES_RATE = (short) 0x0300;// 期汇

	// 内部分类，给股票曾用名用
	public static final short STOCK_WHILOM_NAME_MARKET = (short) 0xF000;

	public static short makeMarket(short type) {
		return (short) (type & 0xF000);
	}

	public static short makeMidMarket(short type) {
		return (short) (type & 0x0F00);
	}

	public static short makeSubMarket(short type) {
		return (short) (type & 0x000F);
	}

	public static short makeBourse(short type) {
		return (short) (type & 0x0F00);
	}

	public static short makeKind(short type) {
		return (short) (type & 0x00FF);
	}

	public static boolean isMarket(short type, short market) {
		return makeMarket(type) == market;
	}

	public static boolean isBourse(short type, short bourse) {
		return makeBourse(type) == bourse;
	}

	public static boolean isKind(short type, short kind) {
		return makeKind(type) == kind;
	}

	public static boolean isMarketBourse(short type, short market, short bourse) {
		return isMarket(type, market) && isBourse(type, bourse);
	}

	public static boolean isMarketBourseKind(short type, short market,
			short bourse, short kind) {
		return isMarket(type, market) && isBourse(type, bourse)
				&& isKind(type, kind);
	}
	
	

}
