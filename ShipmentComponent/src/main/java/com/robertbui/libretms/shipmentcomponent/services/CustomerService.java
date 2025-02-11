package com.robertbui.libretms.shipmentcomponent.services;



import java.util.List;
import java.util.Optional;

import com.robertbui.libretms.shipmentcomponent.domain.entities.CustomerEntity;

public interface CustomerService {
	
	CustomerEntity saveCustomer(String customerCode, CustomerEntity customerEntity);

	CustomerEntity partialUpdateCustomer(String customerCode, CustomerEntity customerEntity);
	
	List<CustomerEntity> findAllCustomers();

	Optional<CustomerEntity> findById(Long id);

	boolean isCustomerExists(String customerCode);

	void deleteCustomer(String customerCode);
}
