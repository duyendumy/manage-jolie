package com.example.demo.model;

import org.springframework.web.multipart.MultipartFile;

public class Image {
	private MultipartFile imageFile;
	private String imageUrl;
	
	
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
	public Image()
	{}
	public Image(MultipartFile imageFile, String imageUrl) {
		super();
		this.imageFile = imageFile;
		this.imageUrl = imageUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	

}
