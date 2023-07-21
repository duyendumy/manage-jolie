package com.example.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.Account;
import com.example.demo.model.Config;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;


	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public Account save(UserRegistrationDto registrationDto, Config config) {
		Account account = new Account(registrationDto.getEmail(), registrationDto.getPassword(), Arrays.asList(config));
		return accountRepository.save(account);
	}

	@Override
	public long countDuplicatedUsername(String username) {
		return accountRepository.countDuplicatedUsername(username);
	}

	@Override
	public Account findByusername(String username) {
		return accountRepository.findByusername(username);
	}

}
