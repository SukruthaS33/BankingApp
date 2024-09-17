package com.sukrutha.bankingApp.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.Repositories.CustomerRepository;
import com.sukrutha.bankingApp.Repositories.RoleRepository;
import com.sukrutha.bankingApp.businessLogics.CustomerBusinessLogic;
import com.sukrutha.bankingApp.customExceptions.InputException;
import com.sukrutha.bankingApp.entities.Customer;
import com.sukrutha.bankingApp.entities.Role;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

	@Autowired
	CustomerBusinessLogic customerBusinessLogic;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleService roleService;

	public String register(Customer customer) {
		log.info("CustomerSerivce:register");
		String customerId = null;

		try {
			log.info("inside try of service");

			if (customer != null) {
				// verifying incoming customer registration
				if (customerBusinessLogic.verifyRegisteringCustomer(customer)) {
					log.info("customer verified and does not exist ");
					log.info(" password " + customer.getCustomerPassword());
					String encryptedPassword = passwordEncoder.encode(customer.getCustomerPassword());
					log.info(" encrypted password " + encryptedPassword);
					customer.setCustomerPassword(encryptedPassword);

					// UPDATE:settingRoles
					Role userRole = roleService.findRoleByName("USER");
					Set<Role> userRoles = new HashSet<Role>();
					userRoles.add(userRole);
					customer.setRoles(userRoles);

					log.info(customer.getCustomerName());
					log.info(customer.getCustomerEmail());
					log.info(customer.getCustomerPassword());
					Customer savedCustomer = customerRepository.save(customer);
					customerId = savedCustomer.getCustomerId();

					if (customerId == null) {
						throw new Exception();
					}
				}

				else {
					throw new Exception("customer already exists");// change to output exception
				}
			}

		} catch (InputException e) {

		} catch (Exception e) {
			log.error("error encountered while registering the customer");
			e.printStackTrace();

		}

		return customerId;
	}

	public boolean login(String customerEmail, String password) {
		log.info("CustomerService::login");
		boolean loggedIn = false;
		try {
			if (customerEmail == null || customerEmail.trim().isEmpty() || password == null
					|| password.trim().isEmpty()) {
				return false;
			}

			Optional<Customer> customerOptional = customerRepository.findByCustomerEmail(customerEmail);
			Customer customer = customerOptional.orElseThrow(() -> new Exception("Customer not found"));

			// Corrected the argument order here
			if (passwordEncoder.matches(password, customer.getCustomerPassword())) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public List<Customer> getAllCustomers() {
		log.info("CustomerService::getAllCustomers");
		List<Customer> allCustomers = new ArrayList<Customer>();
		try {
			allCustomers = customerRepository.findAll();

		} catch (Exception e) {
			log.error("error in getting all customers");
			e.printStackTrace();
		}
		return allCustomers;
	}

	public Customer getCustomerDetailsByCustomerId(String customerId) {
		Customer customer;
		try {
			customer = customerRepository.getReferenceById(customerId);

			if (customer != null) {
				return customer;
			} else {
				throw new Exception();
			}
		}

		catch (Exception e) {
			log.error("internal error in getCustomerDetails()  method");
			e.printStackTrace();
			return null;
		}
	}

	public Customer getCustomerById(String customerId) {
		log.info("CustomerService::getCustomerById");

		try {
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new Exception("customer id is not found"));

			return customer;

		}

		catch (Exception e) {
			log.error("internal error in getCustomerDetails()  method");
			e.printStackTrace();

		}

		return null;
	}

}
