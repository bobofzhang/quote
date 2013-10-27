package com.gildata.quote.client;

public enum PeriodType {

	PERIOD_TYPE_DAY((short) 0x0010), 
	PERIOD_TYPE_MINUTE1((short) 0x00C0), 
	PERIOD_TYPE_MINUTE5((short) 0x0030),
	PERIOD_TYPE_MINUTE15((short) 0x0040), 
	HISDAY((short) 0x0020), 
	MINUTE30((short) 0x0050), 
	MINUTE60((short) 0x0060), 
	PERIOD_TYPE_MINUTE120((short) 0x0070), 
	WEEK((short) 0x0080), 
	MONTH((short) 0x0090), 
	PERIOD_TYPE_DAY_ANY((short) 0x00A0), 
	MINUTE_ANY((short) 0x00B0);

	private final short value;

	PeriodType(short value) {
		this.value = value;
	}

	public static PeriodType fromValue(short value) {
		for (PeriodType type : values()) {
			if (type.value == value) {

				return type;
			}
		}
		return PERIOD_TYPE_DAY;
	}

	public short getValue() {
		return value;
	}
}
