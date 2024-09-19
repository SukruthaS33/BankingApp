package com.sukrutha.bankingApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.Repositories.RoleRepository;
import com.sukrutha.bankingApp.entities.Role;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Role findRoleByName(String roleName) {
		log.info("RoleService findRoleByName");
		Role role = null;
		try {
			 role = roleRepository.findByRoleName(roleName).orElseThrow(() -> new Exception("can't find role"));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return role;
	}

}
