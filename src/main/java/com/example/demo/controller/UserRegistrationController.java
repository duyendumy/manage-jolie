package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.Config;
import com.example.demo.service.AccountService;
import com.example.demo.service.ConfigService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ConfigService configService;

	public UserRegistrationController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	
	@GetMapping
	public String showRegistrationForm(Model model) 
	{
		UserRegistrationDto registrationDto = new UserRegistrationDto();
		model.addAttribute("account", registrationDto);
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("account") UserRegistrationDto registrationDto)
	{
		    long count = accountService.countDuplicatedUsername(registrationDto.getEmail());
		    if(count != 0)
		    {
		    	return "redirect:/registration?fail";
		    }
		    else
		    {
		    	Config config = configService.getConfigByCode("role", "admin");
		    	accountService.save(registrationDto, config);
		    	return "redirect:/registration?success";
		    }
	}

}
