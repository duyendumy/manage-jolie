package com.example.demo.service;


import org.springframework.data.repository.query.Param;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.Account;
import com.example.demo.model.Config;


public interface AccountService{
	Account save(UserRegistrationDto registrationDto, Config config);
	public long countDuplicatedUsername(@Param("username") String username);
	Account findByusername(@Param("username") String username);

}
