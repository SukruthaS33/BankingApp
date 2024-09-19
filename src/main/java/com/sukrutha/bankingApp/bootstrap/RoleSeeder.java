package com.sukrutha.bankingApp.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.sukrutha.bankingApp.Repositories.RoleRepository;
import com.sukrutha.bankingApp.entities.Role;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RoleSeeder implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;

	public RoleSeeder(@Lazy RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			this.loadRoles();
		} catch (Exception e) {
			log.error("error in run");
			e.printStackTrace();
		}

	}

	private void loadRoles() {

		log.info("RoleSeeder::run");
		try {
			Role admin = new Role();
			admin.setRoleId("1");
			admin.setRoleName("ADMIN");
			admin.setDescription("Admin to customer, accounts all privilages");

			Role user = new Role();
			user.setRoleId("2");
			user.setRoleName("USER");
			user.setDescription("bank customers are users");

			roleRepository.save(admin);
			roleRepository.save(user);

		} catch (Exception e) {
			log.error("error in loadRoles()");
			e.printStackTrace();
		}

	}
}
