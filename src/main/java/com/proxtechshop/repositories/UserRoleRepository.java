package com.proxtechshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.UserRole;
import com.proxtechshop.utils.UserRoleId;

@Component
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
	List<UserRole> getByUserId(String userId);
}
