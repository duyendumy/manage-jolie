package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String >{
	
	@Query("SELECT a FROM Account a WHERE a.username =:username")
	Account findByusername(@Param("username") String username);
	
	
	@Query("SELECT COUNT(a) FROM Account a WHERE a.username =:username")
	long countByusername(@Param("username") String username);
	
	@Query("SELECT COUNT(a) FROM Account a WHERE a.username =:username")
	public long countDuplicatedUsername(@Param("username") String username);

}
