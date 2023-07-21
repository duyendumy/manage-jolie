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

import com.example.demo.model.Cart;
import  com.example.demo.model.Product;

@Entity
@Table(name = "detailcart")
public class Detailcart implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCart", nullable = false)
	private Cart cart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProduct", nullable = false)
	private Product product;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "totalPrice", nullable = false, precision = 12, scale = 0)
	private Float totalPrice;

	
	public Detailcart()
	{}
	
	public Detailcart(Cart cart, Product product, Integer quantity, Float totalPrice) {
		super();
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
