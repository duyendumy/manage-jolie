package com.example.demo.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "product")
public class Product implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 500)
	private String name;
	
	@Column(name = "origin", length = 100)
	private String origin;
	
	@Column(name = "description", length = 16777215)
	private String description;
	
	@Column(name = "price", nullable = false, precision = 12, scale = 0)
	private float price;
	
	@Column(name = "inventory", nullable = false)
	private int inventory;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand", nullable = false)
	private Config configByBrand;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category")
	private Config configByCategory;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image")
	private Media media;
	
	@Column(name="status")
	private Integer status;
	


	public Product()
	{}

	public Product(String name, String origin, String description, float price, int inventory,
			Media media) {
		super();
		this.name = name;
		this.origin = origin;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
		this.media = media;
	}
	
	
	public Product(String name, String origin, String description, float price, int inventory,
			Config configByBrand, Config configByCategory, Media media) {
		super();
		this.name = name;
		this.origin = origin;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
		this.configByBrand = configByBrand;
		this.configByCategory = configByCategory;
		this.media = media;
		this.status = 1; // enable product
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

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}
	
	public String getFileBatch() {
		return this.media.getFileBatch();
	}

	public Config getConfigByBrand() {
		return configByBrand;
	}

	public void setConfigByBrand(Config configByBrand) {
		this.configByBrand = configByBrand;
	}

	public Config getConfigByCategory() {
		return configByCategory;
	}

	public void setConfigByCategory(Config configByCategory) {
		this.configByCategory = configByCategory;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
