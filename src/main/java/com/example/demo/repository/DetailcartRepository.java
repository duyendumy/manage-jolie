package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Detailcart;
import com.example.demo.dto.SupportingClass;
import com.example.demo.model.Cart;


@Repository
public interface DetailcartRepository extends JpaRepository<Detailcart,Integer>{

	@Query("SELECT d FROM Detailcart d WHERE d.cart =:cart")
	public List<Detailcart> getAllDetailcartDependCart(@Param("cart") Cart cart); 
	
	@Query("SELECT new com.example.demo.dto.SupportingClass(d.product.id, SUM(d.quantity)) FROM Detailcart AS d WHERE d.cart.id =:id GROUP BY d.product.id")
	public List<SupportingClass> getSoldProduct(@Param("id") Integer id); 
	
}
