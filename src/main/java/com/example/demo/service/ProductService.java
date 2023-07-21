package com.example.demo.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.demo.dto.ProductMediaDto;
import com.example.demo.model.Config;
import com.example.demo.model.Media;
import com.example.demo.model.Product;

public interface ProductService {
	List<Product> getAllProduct();
	void saveProduct(ProductMediaDto productMediaDto,Config configByBrand,Config configByCategory, Media media);
	List<Product> getAllActivedProduct(@Param("status") Integer status); 
	void deleteProduct( @Param("status") Integer status, @Param("id") Integer id);
	Product getProductById(Integer id);
	void saveProductAfterUpdate(Product product);
	List<Product> searchProduct(String keyword);
	void updateInventory( @Param("inventory") int inventory, @Param("id") Integer id);
}
