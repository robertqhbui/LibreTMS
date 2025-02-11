package com.robertbui.libretms.shipmentcomponent.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.robertbui.libretms.shipmentcomponent.domain.dto.CustomerDto;
import com.robertbui.libretms.shipmentcomponent.domain.entities.CustomerEntity;
import com.robertbui.libretms.shipmentcomponent.mappers.Mapper;
import com.robertbui.libretms.shipmentcomponent.services.CustomerService;

import lombok.extern.java.Log;

@Log
@RestController
public class CustomerController {

	private CustomerService customerService;

	private Mapper<CustomerEntity, CustomerDto> customerMapper;

	public CustomerController(CustomerService customerService, Mapper<CustomerEntity, CustomerDto> customerMapper) {
		this.customerService = customerService;
		this.customerMapper = customerMapper;
	}

	@PutMapping(path = "/customers/{code}")
	public ResponseEntity<CustomerDto> createUpdateCustomer(
			@PathVariable("code") String customerCode,
			@RequestBody CustomerDto customerDto) {
		boolean isExists = this.customerService.isCustomerExists(customerCode);
		CustomerEntity customerEntity = this.customerMapper.mapFrom(customerDto);
		CustomerEntity savedCustomerEntity = this.customerService.saveCustomer(customerCode, customerEntity);
		if (isExists)
		{
			return new ResponseEntity<>(this.customerMapper.mapTo(savedCustomerEntity), HttpStatus.OK);
		}
		return new ResponseEntity<>(this.customerMapper.mapTo(savedCustomerEntity), HttpStatus.CREATED);
	}
	
	@PatchMapping(path = "/customers/{code}")
	public ResponseEntity<CustomerDto> partialUpdateCustomer(
			@PathVariable("code") String customerCode,
			@RequestBody CustomerDto customerDto) {
		if (!this.customerService.isCustomerExists(customerCode))
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		CustomerEntity customerEntity = this.customerMapper.mapFrom(customerDto);
		CustomerEntity savedCustomerEntity = this.customerService.partialUpdateCustomer(customerCode, customerEntity);
		return new ResponseEntity<>(this.customerMapper.mapTo(savedCustomerEntity), HttpStatus.OK);
	}
	
	@DeleteMapping (path = "/customers/{code}")
	public ResponseEntity deleteCustomer(@PathVariable("code") String customerCode)
	{
		if (!this.customerService.isCustomerExists(customerCode))
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
		this.customerService.deleteCustomer(customerCode);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	@GetMapping( path = "/customers")
	public List<CustomerDto> getCustomers()
	{
		List<CustomerEntity> customers = this.customerService.findAllCustomers();
		return customers.stream().map(this.customerMapper::mapTo).collect(Collectors.toList());
	}
	
	@GetMapping( path = "/customers/{id}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long id)
	{
		Optional<CustomerEntity> customerFound = this.customerService.findById(id);
		return customerFound.map(customerEntity -> {
			CustomerDto customerDto = this.customerMapper.mapTo(customerEntity);
			return new ResponseEntity<>(customerDto, HttpStatus.OK);
		}
		).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
