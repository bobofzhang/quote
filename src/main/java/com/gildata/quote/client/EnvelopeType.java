package com.gildata.quote.client;


public enum EnvelopeType {
	RT_BEGIN((short)0x0100), //
	RT_END((short)0x0100), //

	RT_COMPASKDATA((short)0x8FFE), // 组合请求类型
	RT_ZIPDATA((short)0x8001), // 压缩返回包类型

	RT_JAVA_MARK((short)0x0010), // JAVA登录 | RT_LOGIN_*
	RT_WINCE_MARK((short)0x0020), // WINCE登录 | RT_LOGIN_*
	RT_NOTZIP((short)0x0040), // 不使用压缩数据传输
	RT_DEBUG((short)0x0080), // 纪录客户请求日志 maxy add 20090908

	RT_INITIALINFO((short)0x0101), // 客户端数据所有初始化
	RT_LOGIN((short)0x0102), // 客户端登录行情服务器
	RT_SERVERINFO((short)0x0103), // 主站信息
	RT_BULLETIN((short)0x0104), // 紧急公告(主推)
	RT_PARTINITIALINFO((short)0x0105), // 客户端数据部分初始化
	RT_ETF_INIT((short)0x0106), // 52只股票数据(ETF)
	RT_LOGIN_HK((short)0x0107), // 客户端登录港股服务器
	RT_LOGIN_FUTURES((short)0x0108), // 客户端登录期货服务器
	RT_LOGIN_FOREIGN((short)0x0109), // 客户端登录外汇服务器
	RT_LOGIN_WP((short)0x010A), // 客户端登录外盘服务器
	RT_LOGIN_INFO((short)0x010B), // 客户端登录资讯服务器
	RT_CHANGE_PWD((short)0x010C), // 修改密码
	// ghm add level2 登陆请求
	RT_LOGIN_LEVEL((short)0x010D), // level2登陆请求
	RT_SERVERINFO2((short)0x010E), // 主站信息2
	RT_VERIFYINFO((short)0x010F), // 用户认证信息返回
	RT_MODIFY_LV2PWD((short)0x0112), // 修改Level2用户密码
	RT_DISCONNLEVEL2((short)0x0113), // level2断线

	RT_REALTIME((short)0x0201), // 行情报价表:1-6乾隆操作键
	RT_DYNREPORT((short)0x0202), // 强弱分析;指标排序;热门板块分析;区间分析;跑马灯股票列表数据;预警
	RT_REPORTSORT((short)0x0203), // 排名报价表:61-66、点击列排序
	RT_GENERALSORT((short)0x0204), // 综合排名报表:81-86
	RT_GENERALSORT_EX((short)0x0205), // 综合排名报表:81-86加入自定义分钟排名
	RT_SEVER_EMPTY((short)0x0206), // 服务器无数据返回空包
	RT_SEVER_CALCULATE((short)0x0207), // 服务器计算数据包,包括涨停、跌停
	RT_ANS_BYTYPE((short)0x0208), // 根据类型返回数据包
	RT_QHMM_REALTIME((short)0x0209), // 期货买卖盘
	RT_LEVEL_REALTIME((short)0x020A), // level
	RT_CLASS_REALTIME((short)0x020B), // 根据分类类别获取行情报价
	// ghm add level2其他数据查询消息
	RT_LEVEL_ORDERQUEUE((short)0x020C), // level2买卖队列
	RT_LEVEL_TRANSACTION((short)0x020D), // LEVEL2逐笔成交
	RT_QHARB_REALTIME((short)0x020E), // 期货套利报价 maxy add
	RT_REALTIME_EXT((short)0x020F), // 扩展的行情报价盘 maxy add

	RT_QZINFO((short)0x0211), // 权证信息
	RT_REPORTSORT_EXT((short)0x0212), // 排名报价表扩展:61-66、点击列排序 maxy add

	RT_SPECIFIEDFIELD_QUERY((short)0x0213), // 指定字段查询 maxy add

	RT_SIMPLE_INITINFO((short)0x040A), // 精简初始化请求（分类与代码关系） add by shaoxh
	RT_SIMPLE_STOCKINFO((short)0x040B), // 精简股票代码查询 add by shaoxh
	RT_SIMPLE_FINANCE((short)0x040C), // 简化最新的财务数据 add by shaoxh
	RT_SIMPLE_XR((short)0x040D), // 简化除权数据 add by shaoxh
	RT_SIMPLE_BLOCK((short)0x040E), // 板块名称或数据查询 add by shaoxh

	RT_TREND((short)0x0301), // 分时走势
	RT_ADDTREND((short)0x0302), // 走势图叠加、多股同列
	RT_BUYSELLPOWER((short)0x0303), // 买卖力道
	RT_HISTREND((short)0x0304), // 历史回忆;多日分时;右小图下分时走势
	RT_TICK((short)0x0305), // TICK图
	RT_ETF_TREND((short)0x0306), // ETF分时走势
	RT_ETF_NOWDATA((short)0x0307), // ETF时时数据
	RT_ETF_TREND_TECH((short)0x0308), // ETFtech分时走势
	RT_HISTREND_INDEX((short)0x0309), // 对于大盘领先-历史回忆;多日分时;右小图下分时走势
	RT_AUTOARBPUSH((short)0x030A), // 期货套利报价 maxy add
	RT_TREND_EXT((short)0x030B), // 扩展的分时请求 maxy add
	RT_HIS_TICK((short)0x030D), // 历史回忆;单日逐笔成交

	RT_TECHDATA((short)0x0400), // 盘后分析
	RT_FILEDOWNLOAD((short)0x0401), // 文件请求（盘后数据下载）
	RT_TECHDATA_EX((short)0x0402), // 盘后分析扩展 -- 支持基金净值
	RT_DATA_WEIHU((short)0x0403), // 数据维护处理
	RT_FILEDOWNLOAD2((short)0x0404), // 下载服务器指定目录文件
	RT_FILED_CFG((short)0x0405), // 配置文件升级/更新
	RT_FILL_DATA((short)0x0406), // 补线处理
	RT_TECHDATA_BYPERIOD((short)0x0407), // 盘后分析扩展 -- 支持不同周期转换
	RT_TECHDATA_INCREMENT((short)0x0408), // 盘后分析扩展 -- 增量数据请求
	RT_TECHDATA_RANGE((short)0x0410), // 分段盘后分析 add by maxy 20090714

	RT_TEXTDATAWITHINDEX_PLUS((short)0x0501), // 正序资讯索引数据
	RT_TEXTDATAWITHINDEX_NEGATIVE((short)0x0502), // 倒序资讯索引数据
	RT_BYINDEXRETDATA((short)0x0503), // 资讯内容数据
	RT_USERTEXTDATA((short)0x0504), // 自定义资讯请求（如菜单等）
	RT_FILEREQUEST((short)0x0505), // 配置文件文件
	RT_FILESimplify((short)0x0506), // 精简文件请求
	RT_ATTATCHDATA((short)0x0507), // 附件数据
	RT_PROMPT_INFO((short)0x0508), // 服务器设置的提示信息
	RT_CODELINK_INFO((short)0x0509), // 关联股票 maxy add

	RT_STOCKTICK((short)0x0601), // 个股分笔、个股详细的分笔数据
	RT_BUYSELLORDER((short)0x0602), // 个股买卖盘
	RT_LIMITTICK((short)0x0603), // 指定长度的分笔请求
	RT_HISTORYTICK((short)0x0604), // 历史的分笔请求
	RT_MAJORINDEXTICK((short)0x0605), // 大盘明细
	RT_VALUE((short)0x0606), // 右小图“值”
	RT_BUYSELLORDER_HK((short)0x0607), // 个股买卖盘(港股）
	RT_BUYSELLORDER_FUTURES((short)0x0608), // 个股买卖盘(期货）
	RT_VALUE_HK((short)0x0609), // 右小图“值”(港股),右小图也发此请求
	RT_VALUE_FUTURES((short)0x060A), // 右小图“值”(期货),左下小图也发此请求
	RT_TOTAL((short)0x060B), // 总持请求包

	RT_LEAD((short)0x0702), // 大盘领先指标
	RT_MAJORINDEXTREND((short)0x0703), // 大盘走势
	RT_MAJORINDEXADL((short)0x0704), // 大盘走势－ADL
	RT_MAJORINDEXDBBI((short)0x0705), // 大盘走势－多空指标
	RT_MAJORINDEXBUYSELL((short)0x0706), // 大盘走势－买卖力道
	RT_SERVERFILEINFO((short)0x0707), // 服务器自动推送要更新的文件信息
	RT_DOWNSERVERFILEINFO((short)0x0708), // 下载-服务器自动推送要更新的文件信息

	RT_CURRENTFINANCEDATA((short)0x0801), // 最新的财务数据
	RT_HISFINANCEDATA((short)0x0802), // 历史财务数据
	RT_EXRIGHT_DATA((short)0x0803), // 除权数据
	RT_HK_RECORDOPTION((short)0x0804), // 港股期权
	RT_BROKER_HK((short)0x0805), // 港股经纪席位下委托情况 // 看我们的服务器是否生成此数据
	RT_BLOCK_DATA((short)0x0806), // 板块数据
	RT_STATIC_HK((short)0x0807), // 港股静态数据

	// zxw add 按代码请求财务数据和除权数据
	RT_FINANCE_BYCODE((short)0x0808), RT_EXRIGHT_BYCODE((short)0x0809),

	RT_USER_BLOKC_DATA((short)0x0810), RT_ONE_BLOCKDATA((short)0x0408), // 一个板块数据请求

	RT_MASSDATA((short)0x0901), // 大单
	RT_SERVERTIME((short)0x0902), // 服务器当前时间
	RT_KEEPACTIVE((short)0x0903), // 保活通信包
	RT_TEST((short)0x0904), // 测试通信包
	RT_TESTSRV((short)0x0905), // 测试客户端到服务器是否通畅
	RT_PUSHINFODATA((short)0x0906), // 资讯实时主推

	RT_AUTOPUSH((short)0x0A01), // 常用主推 // 改RealTimeData 为 CommRealTimeData
	RT_AUTOPUSHSIMP((short)0x0A02), // 精简主推 // 改为请求主推
	RT_REQAUTOPUSH((short)0x0A03), // 请求主推,应用于：预警、跑马灯 // 改RealTimeData 为
							// CommRealTimeData
	RT_ETF_AUTOPUSH((short)0x0A04), // ETF主推
	RT_AUTOBROKER_HK((short)0x0A05), // 经纪主推
	RT_AUTOTICK_HK((short)0x0A06), // 港股分笔主推
	RT_AUTOPUSH_QH((short)0x0A07), // 期货最小主推
	RT_PUSHREALTIMEINFO((short)0x0A08), // 实时解盘主推
	RT_RAW_AUTOPUSH((short)0x0A09), // 数据源原始数据主推

	RT_QHMM_AUTOPUSH((short)0x0A0A), // 期货买卖盘主推
	RT_LEVEL_AUTOPUSH((short)0x0A0B), // level主推
	// ghm add level2其他数据主推消息
	RT_LEVEL_BORDERQUEUE_AUTOPUSH((short)0x0A0C), // LEVEL2买卖队列买方向主推
	RT_LEVEL_SORDERQUEUE_AUTOPUSH((short)0x0A0D), // LEVEL2买卖队列卖方向主推
	RT_LEVEL_TRANSACTION_AUTOPUSH((short)0x0A0E), // LEVEL2逐笔成交主推
	RT_AUTOPUSH_EXT((short)0x0A0F), // 扩展的常用主推 maxy add
	// zxw add level2Cancellation 主推
	RT_LEVEL_TOTALMAX_AUTOPUSH((short)0x0A10), // LEVEL2单笔撤单主推排名
	RT_LEVEL_SINGLEMA_AUTOPUSH((short)0x0A11), // LEVEL2累计撤单主推排名

	RT_AUTOPUSH_RES((short)0x0A12), // 预留的常用主推 maxy add
	RT_AUTOPUSH_LV2RES((short)0x0A13), // 预留的level2主推 maxy add

	RT_SPECIFIEDFIELD_UPDATE((short)0x0A14), // 指定字段更新 maxy add

	// zxw add
	RT_LEVEL_TOTALMAX((short)0x2001), // 查询
	RT_LEVEL_SINGLEMAX((short)0x2002),

	RT_UPDATEDFINANCIALDATA((short)0x0B01), // 增量的财务报表数据
	RT_SYNCHRONIZATIONDATA((short)0x0B02), // 数据同步处理

	//
	RT_Send_Notice((short)0x0C01), // 发表公告
	RT_Send_ScrollText((short)0x0C02), // 发表滚动信息
	RT_Change_program((short)0x0C03), // 更改服务器程序
	RT_Send_File_Data((short)0x0C04), // 发送文件到服务器
	RT_RequestDBF((short)0x0C05), // 请求DBF文件

	RT_InfoSend((short)0x0C06), // 发布信息
	RT_InfoUpdateIndex((short)0x0C07), // 更新信息索引
	RT_InfoUpdateOneIndex((short)0x0C08), // 更新一条信息索引
	RT_NoteMsgData((short)0x0C09), // 定制短信数据传送
	RT_InfoDataTransmit((short)0x0C0A), // 验证转发
	RT_InfoCheckPurview((short)0x0C0B), // 返回注册详细分类信息
	RT_InfoClickTime((short)0x0C0C), // 点击次数

	RT_REPORTSORT_Simple((short)0x0D01), // 排名报价表:61-66、点击列排序（精简）
	RT_PARTINITIALINFO_Simple((short)0x0D02), // 代码返回
	RT_RETURN_EMPTY((short)0x0D03), // 返回空的数据包
	RT_InfoDataRailing((short)0x0D04), // 请求栏目

	// wince 相关
	RT_WINCE_FIND((short)0x0E01), // 查找代码
	RT_WINCE_UPDATE((short)0x0E02), // CE版本升级
	RT_WINCE_ZIXUN((short)0x0E03), // CE资讯请求

	RT_Srv_SrvStatus((short)0x0F01), // 后台程序运行状态
	RT_SRV_SYNC((short)0x0F02),
	
	UNKNOWN((short) 0xFFFF);

	private final short value;

	EnvelopeType(short value) {
		this.value = value;
	}
	
    public static EnvelopeType fromValue(short value) {
        for (EnvelopeType type : values()) {
            if (type.value == value) {
            	
                return type;
            }
        }
        return UNKNOWN;
    }

	public short getValue() {
		return value;
	}
	
	
}
