package com.robertbui.libretms.shipmentcomponent.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;

import com.robertbui.libretms.shipmentcomponent.domain.entities.CustomerEntity;
import com.robertbui.libretms.shipmentcomponent.repositories.CustomerRepository;
import com.robertbui.libretms.shipmentcomponent.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public CustomerEntity saveCustomer(String customerCode, CustomerEntity customerEntity) {
		Iterable<CustomerEntity> customers = customerRepository.findByCode(customerCode);
		customers.forEach(customer -> {
			customerEntity.setId(customer.getId());
		});
		customerEntity.setCode(customerCode);
		return this.customerRepository.save(customerEntity);
	}
	
	@Override
	public CustomerEntity partialUpdateCustomer(String customerCode, CustomerEntity customerEntity) {
		customerEntity.setCode(customerCode);
		Iterable<CustomerEntity> customers = this.customerRepository.findByCode(customerCode);
		if (customers.iterator().hasNext())
		{
			CustomerEntity existingCustomerEntity = customers.iterator().next();
			Optional.ofNullable(customerEntity.getName()).ifPresent(existingCustomerEntity::setName);
			Optional.ofNullable(customerEntity.getIsActive()).ifPresent(existingCustomerEntity::setIsActive);
			return this.customerRepository.save(existingCustomerEntity);
		}
		throw new RuntimeException("Customer does not exist");
	}
	
	@Override
	public void deleteCustomer(String customerCode) {
		
		Iterable<CustomerEntity> customers = this.customerRepository.findByCode(customerCode);
		if (customers.iterator().hasNext())
		{
			CustomerEntity existingCustomerEntity = customers.iterator().next();
			this.customerRepository.deleteById(existingCustomerEntity.getId());
		}	
	}

	
	@Override
	public List<CustomerEntity> findAllCustomers() {
		return StreamSupport.stream(this.customerRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<CustomerEntity> findById(Long id) {
		return this.customerRepository.findById(id);
	}

	@Override
	public boolean isCustomerExists(String customerCode) {
		return this.customerRepository.existsCustomerEntityByCode(customerCode);
	}




}
