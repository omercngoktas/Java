package com.omercngoktas.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.omercngoktas.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
