package com.sukrutha.bankingApp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.Repositories.CustomerRepository;
import com.sukrutha.bankingApp.Repositories.CustomerRequestRepository;
import com.sukrutha.bankingApp.entities.Customer;
import com.sukrutha.bankingApp.entities.CustomerRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerRequestService {

	@Autowired
	CustomerRequestRepository customerRequestRepository;

	@Autowired
	CustomerRepository customerRepository;

	public List<CustomerRequest> getAllRequestsofAllCustomers() {
		log.info("CustomerRequestService::getAllRequestsofAllCustomers");
		List<CustomerRequest> allRequests = new ArrayList<CustomerRequest>();
		try {
			allRequests = customerRequestRepository.findAllSubmittedRequests();
			return allRequests;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error in getting all the requests");
		}
		return null;

	}

	public CustomerRequest getRequestedItemById(String requestId) {
		log.info("CustomerRequestService::getRequestedItemById" + requestId);
		try {
			CustomerRequest customerRequest = customerRequestRepository.findById(requestId)
					.orElseThrow(() -> new Exception());

			return customerRequest;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("unable to fetch the request");
		}
		return null;

	}

	public CustomerRequest createNewRequestForCustomer(CustomerRequest request, String customerId) {
		log.info("CustomerRequestService::createNewRequestForCustomer");
		CustomerRequest newRequest = null;
		try {

			Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new Exception());
			request.setCustomer(customer);
			request.setRequestStatus("SUBMITTED");
			newRequest = customerRequestRepository.save(request);

			return newRequest;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("unable to fetch the request");
		}
		return newRequest;

	}

	public boolean approveOrRejectCustomerRequest(String requestId, String requestStatus, String adminComment) {
		log.info("CustomerRequestService::approveOrRejectCustomerRequest " + requestId + " " + requestStatus);
		boolean approveOrRejectStatus = false;
		try {

			CustomerRequest customerRequest = this.getRequestedItemById(requestId);
			log.info(customerRequest + " request is");
			customerRequest.setRequestStatus(requestStatus);
			log.info("adminComment"+adminComment);
			if(adminComment!="" || adminComment!=null) {
				customerRequest.setAdminComment(adminComment);
			}
			customerRequestRepository.save(customerRequest);

			approveOrRejectStatus = true;
			return approveOrRejectStatus;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("unable to approve/reject a request");
		}
		return approveOrRejectStatus;

	}

}
