package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Billinfo;
import com.example.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	@Query("SELECT p FROM Product p WHERE p.status =:status")
	public List<Product> getAllActivedProduct(@Param("status") Integer status); 
	
	@Modifying
	@Query("UPDATE Product p SET p.status = :status WHERE p.id LIKE :id")
	void deleteProduct( @Param("status") Integer status, @Param("id") Integer id);
	
	@Query("SELECT p FROM Product p WHERE CONCAT(p.id, p.name, p.origin, p.configByBrand.code, p.configByCategory.code) LIKE %?1%")
	public List<Product> searchProduct(String keyword);
	
	@Modifying
	@Query("UPDATE Product p SET p.inventory = :inventory WHERE p.id LIKE :id")
	void updateInventory( @Param("inventory") int inventory, @Param("id") Integer id);
}
