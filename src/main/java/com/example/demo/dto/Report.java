package com.example.demo.dto;

import java.util.Date;

public class Report {
	private String beginDate;
	private String endDate;
	
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public Report()
	{}
	public Report(String beginDate, String endDate) {
		super();
		this.beginDate = beginDate;
		this.endDate = endDate;
	}
	

}
