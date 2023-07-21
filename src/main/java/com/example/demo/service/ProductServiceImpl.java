package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ProductMediaDto;
import com.example.demo.model.Config;
import com.example.demo.model.Media;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{


	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}
	
	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}


	@Override
	public void saveProduct(ProductMediaDto productMediaDto, Config configByBrand, Config configByCategory,
			Media media) {
		Product product = new Product(productMediaDto.getName(),productMediaDto.getOrigin(), 
				productMediaDto.getDescription(), productMediaDto.getPrice(),
				productMediaDto.getInventory(),configByBrand, configByCategory, media);
		 this.productRepository.save(product);	
		
	}

	@Override
	@Transactional
	public void deleteProduct(Integer status, Integer id) {
		 this.productRepository.deleteProduct(status, id);
		
	}

	@Override
	public List<Product> getAllActivedProduct(Integer status) {
		 return productRepository.getAllActivedProduct(status);
	}

	@Override
	public Product getProductById(Integer id) {
		Optional<Product> optional = productRepository.findById(id);
		Product product = null;
		if (optional.isPresent()) {
			product = optional.get();
		} else {
			throw new RuntimeException("Product not found for id:" + id);
		}
		return product;
	}

	@Override
	public void saveProductAfterUpdate(Product product) {
		this.productRepository.save(product);
		
	}

	@Override
	public List<Product> searchProduct(String keyword) {
		if (keyword != null) {
		     return productRepository.searchProduct(keyword);
		}
		return null;
	}

	@Override
	@Transactional
	public void updateInventory(int inventory, Integer id) {
		this.productRepository.updateInventory(inventory, id);	
	}

}
