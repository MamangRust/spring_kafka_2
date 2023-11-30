package com.sanedge.paymentservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.sanedge.paymentservice.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
