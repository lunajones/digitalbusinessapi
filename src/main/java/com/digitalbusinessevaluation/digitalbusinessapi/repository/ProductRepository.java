package com.digitalbusinessevaluation.digitalbusinessapi.repository;

import org.springframework.stereotype.Repository;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.Product;
import com.digitalbusinessevaluation.digitalbusinessapi.repository.base.BaseRepository;

@Repository
public interface ProductRepository extends BaseRepository<Product> {

	Product findByName(String name);
}