package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Config;

public class ProductMediaDto {

	private Integer id;
	private String name;
	private String origin;
	private String description;
	private float price;
	private int inventory;
	private String codeConfigByBrand;
	private String codeConfigByCategory;
	private MultipartFile imageFile;
	
	
	public ProductMediaDto()
	{}
	
	
	
	public ProductMediaDto(String name, String origin, String description, float price,
			int inventory, MultipartFile imageFile) {
		super();
		this.name = name;
		this.origin = origin;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
		this.imageFile = imageFile;

	}

	public ProductMediaDto(String name, String origin, String description, float price, int inventory,
			String codeConfigByBrand, String codeConfigByCategory, MultipartFile imageFile) {
		super();
		this.name = name;
		this.origin = origin;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
		this.codeConfigByBrand = codeConfigByBrand;
		this.codeConfigByCategory = codeConfigByCategory;
		this.imageFile = imageFile;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}


	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	
	public String getCodeConfigByBrand() {
		return codeConfigByBrand;
	}



	public void setCodeConfigByBrand(String codeConfigByBrand) {
		this.codeConfigByBrand = codeConfigByBrand;
	}



	public String getCodeConfigByCategory() {
		return codeConfigByCategory;
	}



	public void setCodeConfigByCategory(String codeConfigByCategory) {
		this.codeConfigByCategory = codeConfigByCategory;
	}



	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	
}
