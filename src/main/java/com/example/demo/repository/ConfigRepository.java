package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config,Integer >{
	
	@Query("SELECT c FROM Config c WHERE c.groupcode =:groupcode AND c.code =:code")
	public Config getConfigByCode(@Param("groupcode") String groupcode, @Param("code") String code);
	
	@Query("SELECT c FROM Config c WHERE c.id =:id")
	public Config getConfigById(@Param("id") Integer id);
	
	@Query("SELECT c FROM Config c WHERE c.groupcode =:groupcode")
	public List<Config> getConfigByGroupcode(@Param("groupcode") String groupcode);

}
