package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SupportingClass;
import com.example.demo.model.Cart;
import com.example.demo.model.Detailcart;


public interface DetailcartService {
	public List<Detailcart> getAllDetailcartDependCart(Cart cart); 
	public List<Detailcart> getAllDetailcart(); 
	public List<SupportingClass> getSoldProduct(Integer id); 
}
