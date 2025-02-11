package com.robertbui.libretms.shipmentcomponent.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.robertbui.libretms.shipmentcomponent.domain.dto.CustomerDto;
import com.robertbui.libretms.shipmentcomponent.domain.entities.CustomerEntity;
import com.robertbui.libretms.shipmentcomponent.mappers.Mapper;

@Component
public class CustomerMapperImpl implements Mapper<CustomerEntity, CustomerDto>{
	private ModelMapper modelMapper;
	
	
	public CustomerMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	@Override
	public CustomerDto mapTo(CustomerEntity customerEnity) {
		return modelMapper.map(customerEnity, CustomerDto.class);
	}

	@Override
	public CustomerEntity mapFrom(CustomerDto customerDto) {
		return modelMapper.map(customerDto, CustomerEntity.class);
	}
	
}


