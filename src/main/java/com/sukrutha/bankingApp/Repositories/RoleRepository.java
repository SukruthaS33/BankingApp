package com.sukrutha.bankingApp.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sukrutha.bankingApp.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

	public Optional<Role> findByRoleName(String roleName);
	
}