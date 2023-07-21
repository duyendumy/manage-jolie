package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Detailcart;
import com.example.demo.dto.SupportingClass;
import com.example.demo.model.Cart;
import com.example.demo.repository.DetailcartRepository;

@Service
public class DetailcartServiceImpl implements DetailcartService {

	@Autowired
	private DetailcartRepository detailcartRepository;
	
	@Override
	public List<Detailcart> getAllDetailcartDependCart(Cart cart) {
		return detailcartRepository.getAllDetailcartDependCart(cart);
	}

	@Override
	public List<Detailcart> getAllDetailcart() {
		return detailcartRepository.findAll();
	}


	@Override
	public List<SupportingClass> getSoldProduct(Integer id) {
		return detailcartRepository.getSoldProduct(id);
	}

}
