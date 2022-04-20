package com.egemen.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egemen.springboot.entity.Vendor;

@Repository
public interface IVendorRepo extends JpaRepository<Vendor, Long> {

}
