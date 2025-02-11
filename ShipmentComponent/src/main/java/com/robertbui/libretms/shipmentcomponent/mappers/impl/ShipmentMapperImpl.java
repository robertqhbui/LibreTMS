package com.robertbui.libretms.shipmentcomponent.mappers.impl;
import com.robertbui.libretms.shipmentcomponent.domain.dto.ShipmentDto;
import com.robertbui.libretms.shipmentcomponent.domain.entities.ShipmentEntity;
import com.robertbui.libretms.shipmentcomponent.mappers.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ShipmentMapperImpl implements Mapper<ShipmentEntity, ShipmentDto>{

	private ModelMapper modelMapper;
	
	
	public ShipmentMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public ShipmentDto mapTo(ShipmentEntity shipmentEntity) {
		return modelMapper.map(shipmentEntity, ShipmentDto.class);
		
	}

	@Override
	public ShipmentEntity mapFrom(ShipmentDto shipmentDto) {
		return modelMapper.map(shipmentDto, ShipmentEntity.class);
	}


}
