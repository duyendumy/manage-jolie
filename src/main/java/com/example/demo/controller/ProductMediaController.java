package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.demo.dto.ProductMediaDto;
import com.example.demo.model.Config;
import com.example.demo.model.Media;
import com.example.demo.service.ConfigService;
import com.example.demo.service.MediaService;
import com.example.demo.service.ProductService;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

@Controller
public class ProductMediaController {
	


	@Autowired
	private ProductService productService;
	

	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private ConfigService configService;
	

	@Autowired
	private Storage storage;
	
	
	@GetMapping("/showNewProductForm")
	public String showNewMediaForm(Model model)
	{  // create model attribute to bind form data
		ProductMediaDto productMediaDto = new ProductMediaDto();
		model.addAttribute("product",productMediaDto);
		return "new_form_product";	
	}
	
	
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("product") ProductMediaDto productMediaDto)
	{  
		if (!productMediaDto.getImageFile().isEmpty()) {
			String fileName = productMediaDto.getImageFile().getOriginalFilename();
			BlobId blogId = BlobId.of("manage-jolie", "images/" + fileName);
			BlobInfo blogInfo = BlobInfo.newBuilder(blogId).setContentType("images/png").build();
			try {
				byte[] data = productMediaDto.getImageFile().getBytes();
			//	storage.create(blogInfo, data);
				String url = "https://storage.googleapis.com/manage-jolie/images/" + fileName;
				
				// create media and save in database
				Media media = new Media(url,fileName);
				Media savedMedia = mediaService.saveMedia(media);
				
				// check code brand and code category are existed
				Config configByBrand = configService.getConfigByCode("brand", productMediaDto.getCodeConfigByBrand());
				Config configByCategory = configService.getConfigByCode("category", productMediaDto.getCodeConfigByCategory());
			    if(configByBrand == null)
			    {
			    	configByBrand = new Config("brand",productMediaDto.getCodeConfigByBrand());
			    	configService.saveConfig(configByBrand);
			    }
			    if(configByCategory == null)
			    {
			    	configByCategory = new Config("category",productMediaDto.getCodeConfigByCategory());
			    	configService.saveConfig(configByCategory);	    	
			    }
				
				productService.saveProduct(productMediaDto,configByBrand, configByCategory,savedMedia);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/product";
	}

}
