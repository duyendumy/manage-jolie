package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.Report;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.Account;
import com.example.demo.model.Billinfo;
import com.example.demo.service.AccountService;
import com.example.demo.service.BillinfoService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class MainController {
	
	 private boolean isLoggedIn = false;
	 
	 static String email="guest";

	@Autowired
	private BillinfoService billinfoService;

	@Autowired
	private AccountService accountService;

	@PostMapping("/login")
	public String login(@ModelAttribute("username") String username, @ModelAttribute("password") String password) {
		Account existingUser = accountService.findByusername(username);
		if (existingUser != null && existingUser.getPassword().equals(password)) {
			 isLoggedIn = true;
			 this.email = existingUser.getUsername();
			 return "redirect:/";
		}
         return "redirect:/login?error";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String viewHomePage(Model model) {
		if (isLoggedIn) {
		long numberOfNewBill = billinfoService.countBillinfo(1);
		long numberOfProcessingBill = billinfoService.countBillinfo(2);
		Float totalPriceOfSuccessfulBill = billinfoService.sumTotalPrice(3);
		model.addAttribute("username", email);
		model.addAttribute("numberOfNewBill", numberOfNewBill);
		model.addAttribute("numberOfProcessingBill", numberOfProcessingBill);
		model.addAttribute("totalPriceOfSuccessfulBill", totalPriceOfSuccessfulBill);
		return "index";}
		else {
			return "redirect:/login";
		}
	}
	
	   @GetMapping("/logout")
	    public String logout() {
	        isLoggedIn = false; 
	        return "redirect:/login?logout";
	    }

}
