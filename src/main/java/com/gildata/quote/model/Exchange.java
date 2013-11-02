package com.gildata.quote.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Exchange {

	private String code;
	private int date;
	private List<Integer> times;

	public Exchange(String code) {
		super();
		this.code = code;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public List<Integer> getTimes() {
		if (times == null){
			times = new ArrayList<Integer>();
		}
		return times;
	}

	public void setTimes(List<Integer> times) {
		this.times = times;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	@Override
	public boolean equals(Object obj) {
		   if (obj == null) { return false; }
		   if (obj == this) { return true; }
		   if (obj.getClass() != getClass()) {
		     return false;
		   }
		   Exchange rhs = (Exchange) obj;
		   return new EqualsBuilder()
		                 .appendSuper(super.equals(obj))
		                 .append(code, rhs.code)
		                 .isEquals();
	}

}
