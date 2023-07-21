package com.example.demo.service;

import org.springframework.data.repository.query.Param;

import com.example.demo.model.Cart;

public interface CartService {
	public Cart getCartDependId(@Param("id") Integer id); 
}
