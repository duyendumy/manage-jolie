package com.example.demo.dto;


public class SupportingClass {
	private Integer product;
	private Long quantity;
	
	
	public SupportingClass(Integer product, Long quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public SupportingClass() {
	}
	
	public Integer getProduct() {
		return product;
	}
	public void setProduct(Integer product) {
		this.product = product;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	

}
