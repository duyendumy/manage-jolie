package com.example.demo.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "media")
public class Media implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	

	@Column(name = "description", length = 1000)
	private String description;
	
	@Column(name = "fileBatch", length = 1000)
	private String fileBatch;
	
	@Column(name = "fileName", length = 1000)
	private String fileName;
	
	public String getFileBatch() {
		return fileBatch;
	}

	public void setFileBatch(String fileBatch) {
		this.fileBatch = fileBatch;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public Media(String description, String fileBatch, String fileName) {
		super();
		this.description = description;
		this.fileBatch = fileBatch;
		this.fileName = fileName;
	}
	
	public Media(String fileBatch, String fileName) {
		super();
		this.fileBatch = fileBatch;
		this.fileName = fileName;
	}

	public Media() {
	}
	
	
}
