package com.proxtechshop.testbean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.proxtechshop.entities.Role;
import com.proxtechshop.repositories.RoleRepository;

@Configuration
public class GlobalBeanTest {
	
	@Autowired
	RoleRepository roleRepo;
	
	@Bean(name = "roles")
	public List<Role> AllRoles(){
		return roleRepo.findAll();
	}
	
}
