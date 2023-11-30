package com.sanedge.stockservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.sanedge.stockservice.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
