package com.example.demo.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Config;
import com.example.demo.repository.ConfigRepository;

@Service
public class ConfigServiceImpl implements ConfigService{

	@Autowired
	private ConfigRepository configRepository;
	
	@Override
	public Config getConfigByCode(String groupcode, String code) {
		return this.configRepository.getConfigByCode(groupcode,code);
	}

	@Override
	public Config getConfigById(Integer id) {
		return this.configRepository.getConfigById(id);
	}

	@Override
	public void saveConfig(Config Config) {
		 this.configRepository.save(Config);
		
	}

	@Override
	public List<Config> getConfigByGroupcode(String groupcode) {
		return  this.configRepository.getConfigByGroupcode(groupcode);
	}



	



}
