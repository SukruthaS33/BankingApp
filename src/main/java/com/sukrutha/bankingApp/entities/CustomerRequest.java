package com.sukrutha.bankingApp.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "customer_requests")  // It's better to name the table in plural
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String requestId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")  // Mapping the foreign key
    private Customer customer;  // The type should be Customer, not customerId

    @Column(name = "request_name")
    private String requestName;

    @Column(name = "request_desc")
    private String requestFullDesc;

    @Column(name = "request_ts")
    private LocalDateTime requestTimeStamp;

    @Column(name = "request_status")
    private String requestStatus;

    @Column(name = "request_completion_time")
    private LocalDateTime requestCompletionTimeStamp;
    

}
