package com.egemen.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.egemen.springboot.entity.Vendor;
import com.egemen.springboot.repository.IVendorRepo;

@RestController
public class VendorController {
	
	@Autowired
	IVendorRepo vendorRepo;
	
	@PostMapping("/vendors")
	public ResponseEntity<Vendor> save(@RequestBody Vendor vendor) {
		try {
			return new ResponseEntity<>(vendorRepo.save(vendor),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/vendors")
    public ResponseEntity<List<Vendor>> getAllVendors() {
		try {
			List<Vendor> list = vendorRepo.findAll();
			if (list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<List<Vendor>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Vendor>>(list, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/vendors/{id}")
    public ResponseEntity<Vendor> getSingleVendor(@PathVariable Long id) {
		Optional<Vendor> vendor = vendorRepo.findById(id);
		if (vendor.isPresent()) {
			return new ResponseEntity<Vendor>(vendor.get(),HttpStatus.OK);
		}
		return new ResponseEntity<Vendor>(HttpStatus.NOT_FOUND);
	}
}
