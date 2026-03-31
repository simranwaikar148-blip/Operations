package com.minatech.operations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minatech.operations.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    // Optional: add methods like findByEmployeeId if needed
}