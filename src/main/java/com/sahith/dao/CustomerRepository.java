package com.sahith.dao;


import com.sahith.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public Customer findByEmail(String email);

}
