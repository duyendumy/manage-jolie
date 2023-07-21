package com.example.demo.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "account")
public class Account implements java.io.Serializable{

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 100)
	private String username;

	@Column(name = "password", length = 100)
	private String password;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "account_roles",
			joinColumns = @JoinColumn(
		            name = "account_username", referencedColumnName = "username"),
			inverseJoinColumns = @JoinColumn(
				            name = "role_id", referencedColumnName = "id"))
	private Collection<Config> configByRole;


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Collection<Config> getConfigByRole() {
		return configByRole;
	}


	public void setConfigByRole(Collection<Config> configByRole) {
		this.configByRole = configByRole;
	}
	
	public Account()
	{}


	public Account(String username, String password, Collection<Config> configByRole) {
		super();
		this.username = username;
		this.password = password;
		this.configByRole = configByRole;
	}
	

	
	
	
}
