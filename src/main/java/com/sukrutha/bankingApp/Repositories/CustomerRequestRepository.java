package com.sukrutha.bankingApp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sukrutha.bankingApp.entities.CustomerRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRequestRepository extends JpaRepository<CustomerRequest, String> {

	@Query("SELECT c FROM CustomerRequest c WHERE c.requestStatus = 'SUBMITTED'")
	List<CustomerRequest> findAllSubmittedRequests();
}
