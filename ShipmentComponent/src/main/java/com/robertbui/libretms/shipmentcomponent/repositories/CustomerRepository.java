package com.robertbui.libretms.shipmentcomponent.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.robertbui.libretms.shipmentcomponent.domain.entities.CustomerEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
	public Iterable<CustomerEntity> findByCode(String code);
	
	public boolean existsCustomerEntityByCode(String code);

}