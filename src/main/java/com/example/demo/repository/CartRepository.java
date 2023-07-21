package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer >{

	@Query("SELECT c FROM Cart c WHERE c.id =:id")
	public Cart getCartDependId(@Param("id") Integer id); 
}
