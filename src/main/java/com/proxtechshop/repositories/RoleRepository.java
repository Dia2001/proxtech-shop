package com.proxtechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Role;

@Component
public interface RoleRepository extends JpaRepository<Role, String> {
	
}
