package com.proxtechshop.api.request;

import java.util.Date;

public class ProductStatisticsDayStartDayEndRequest {
	private Date startDate;
	
	private Date endDate;
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
