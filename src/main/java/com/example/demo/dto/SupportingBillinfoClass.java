package com.example.demo.dto;

import java.util.Date;

public class SupportingBillinfoClass {

	private Double totalPrice;
	
	private Date datePayment;

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}

	public SupportingBillinfoClass()
	{}
	
	public SupportingBillinfoClass( Date datePayment,Double totalPrice) {
		this.datePayment = datePayment;
		this.totalPrice = totalPrice;
	}
	
	
}
