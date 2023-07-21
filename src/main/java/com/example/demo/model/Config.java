package com.example.demo.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "config")
public class Config implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "groupcode", length = 500)
	private String groupcode;
	
	@Column(name = "code", length = 500)
	private String code;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Config()
	{}
	
	public Config(String groupcode, String code) {
		super();
		this.groupcode = groupcode;
		this.code = code;
	}

	public Object stream() {
		// TODO Auto-generated method stub
		return null;
	}

}
