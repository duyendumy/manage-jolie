package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

 

import com.example.demo.model.Config;
import com.example.demo.model.Product;
import com.example.demo.service.ConfigService;
import com.example.demo.service.ProductService;



@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ConfigService configService;

	// display list of product
	@GetMapping("/product")
	public String viewMedia(Model model) {
		List<Product> listProducts = productService.getAllActivedProduct(1);
		System.out.println(listProducts);

		List<String> nameProducts = new ArrayList<>();
		List<String> descriptons = new ArrayList<>();
		List<String> brands= new ArrayList<>();
		List<String> origins= new ArrayList<>();
		List<String> categories= new ArrayList<>();
		List<Float> prices= new ArrayList<>();
		List<Integer> inventories= new ArrayList<>();
		for(int i = 0; i < listProducts.size();i++) {
			nameProducts.add(listProducts.get(i).getName());
			descriptons.add(listProducts.get(i).getDescription());
			brands.add(listProducts.get(i).getConfigByBrand().getGroupcode());
			origins.add(listProducts.get(i).getOrigin());
			categories.add(listProducts.get(i).getConfigByCategory().getGroupcode());
			prices.add(listProducts.get(i).getPrice());
			inventories.add(listProducts.get(i).getInventory());
		}
		model.addAttribute("myName", nameProducts);	
		model.addAttribute("myDescription", descriptons);
		model.addAttribute("myBrand", brands);
		model.addAttribute("myOrigin", origins);
		model.addAttribute("myCatgeory", categories);
		model.addAttribute("myPrice", prices);
		model.addAttribute("myInventory", inventories);
		return "product";
	}

	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(value = "id") Integer id) {
		// call delete product method
		this.productService.deleteProduct(0, id);
		return "redirect:/product";

	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {
		Product product = productService.getProductById(id);
		List<Config> listConfigBrand = configService.getConfigByGroupcode("brand");
		List<Config> listConfigCategory = configService.getConfigByGroupcode("category");
		model.addAttribute("product", product);
		model.addAttribute("listConfigBrand", listConfigBrand);
		model.addAttribute("listConfigCategory", listConfigCategory);
		return "update_product";

	}

	@PostMapping("/saveProductAfterUpdate")
	public String saveProductAfterUpdate(@ModelAttribute("product") Product product) {
		product.setStatus(1);
		this.productService.saveProductAfterUpdate(product);
		return "redirect:/product";
	}

	@GetMapping("/searchProduct")
	public String searchProduct(Model model, @Param("keyword") String keyword) {
		List<Product> listProduct = productService.searchProduct(keyword);
		System.out.print("Product neeeee" + listProduct);
		List<Product> listActiveProduct = new ArrayList<Product>();
		for (Product i: listProduct) {
			if (i.getStatus() == 1) {
				listActiveProduct.add(i);
			}
		}
		model.addAttribute("listProducts", listActiveProduct);
		return "product";
	}

}
