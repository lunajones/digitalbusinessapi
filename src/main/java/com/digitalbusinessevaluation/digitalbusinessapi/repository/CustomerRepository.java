package com.digitalbusinessevaluation.digitalbusinessapi.repository;

import org.springframework.stereotype.Repository;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.Customer;
import com.digitalbusinessevaluation.digitalbusinessapi.repository.base.BaseRepository;

@Repository
public interface CustomerRepository extends BaseRepository<Customer> {

	Customer findByName(String name);
}