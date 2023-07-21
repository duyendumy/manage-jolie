package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.model.Detailcart;

/**
 * Cart generated by hbm2java
 */
@Entity
@Table(name = "cart")
public class Cart implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "totalPrice", nullable = false, precision = 12, scale = 0)
	private float totalPrice;
	
	@Column(name = "status")
	private Integer status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
	private Set<Detailcart> detailcarts = new HashSet<Detailcart>(0);
	
	public Cart(float totalPrice, Integer status, Set<com.example.demo.model.Detailcart> detailcarts,
			Set<Billinfo> billinfos) {
		super();
		this.totalPrice = totalPrice;
		this.status = status;
		this.detailcarts = detailcarts;
		this.billinfos = billinfos;
	}

	public Set<Detailcart> getDetailcarts() {
		return detailcarts;
	}

	public void setDetailcarts(Set<Detailcart> detailcarts) {
		this.detailcarts = detailcarts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
	private Set<Billinfo> billinfos = new HashSet<Billinfo>(0);

	public Cart() {
	}

	public Cart( float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Cart(float totalPrice, Integer status, 
			Set<Billinfo> billinfos) {
		this.totalPrice = totalPrice;
		this.status = status;
		this.billinfos = billinfos;
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public float getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public Set<Billinfo> getBillinfos() {
		return this.billinfos;
	}

	public void setBillinfos(Set<Billinfo> billinfos) {
		this.billinfos = billinfos;
	}

}