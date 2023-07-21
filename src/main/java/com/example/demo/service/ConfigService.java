package com.example.demo.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.demo.model.Config;


public interface ConfigService {
	Config getConfigByCode(@Param("groupcode") String groupcode, @Param("code") String code);
	Config getConfigById(@Param("Id") Integer id);
	void saveConfig(Config Config);
	public List<Config> getConfigByGroupcode(@Param("groupcode") String groupcode);
}
