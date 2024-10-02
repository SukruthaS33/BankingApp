package com.sukrutha.bankingApp.bootstrap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sukrutha.bankingApp.Repositories.CustomerRepository;
import com.sukrutha.bankingApp.Repositories.RoleRepository;
import com.sukrutha.bankingApp.entities.Customer;
import com.sukrutha.bankingApp.entities.Role;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RoleSeeder implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	CustomerRepository customerRepository;

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
		
			Customer admin1 = new Customer();
			admin1.setCustomerName("ADMIN2");
			admin1.setCustomerEmail("ADMIN2@gmail.com");
			admin1.setCustomerPassword(this.encodeAdminPassword());
			admin1.setAge(34);
		
			Set<Role> adminRole = new HashSet<Role>();
			adminRole.add(admin);
			admin1.setRoles(adminRole);

			
			roleRepository.save(admin);
			roleRepository.save(user);
			//customerRepository.save(admin1);
			
			

		} catch (Exception e) {
			log.error("error in loadRoles()");
			e.printStackTrace();
		}

	}
	
	private String encodeAdminPassword() {
		String password = "password";
		String encodedPassword = passwordEncoder.encode(password);
		return encodedPassword;
	}
}
