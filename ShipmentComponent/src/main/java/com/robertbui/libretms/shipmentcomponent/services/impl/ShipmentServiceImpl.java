package com.robertbui.libretms.shipmentcomponent.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.robertbui.libretms.shipmentcomponent.domain.dto.ShipmentDto;
import com.robertbui.libretms.shipmentcomponent.domain.entities.ShipmentEntity;
import com.robertbui.libretms.shipmentcomponent.repositories.ShipmentRepository;
import com.robertbui.libretms.shipmentcomponent.services.ShipmentService;

@Service
public class ShipmentServiceImpl implements ShipmentService {

	private ShipmentRepository shipmentRepository;
	
	public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
		this.shipmentRepository = shipmentRepository;
	}

	@Override
	public ShipmentEntity saveShipment(ShipmentEntity shipmentEntity) {
		return this.shipmentRepository.save(shipmentEntity);
	}
	
	@Override
	public ShipmentEntity partialUpdateShipment(Long id, ShipmentEntity shipmentEntity) {
		Optional<ShipmentEntity> shipmentFound = this.shipmentRepository.findById(id);
		return shipmentFound.map(existingShipmentEntity -> {
			Optional.ofNullable(shipmentEntity.getDeliveryInstruction()).ifPresent(existingShipmentEntity::setDeliveryInstruction);
			Optional.ofNullable(shipmentEntity.getActualShippingCost()).ifPresent(existingShipmentEntity::setActualShippingCost);
			return this.shipmentRepository.save(existingShipmentEntity);
		}).orElseThrow();
	}
	
	@Override
	public void deleteShipment(Long id) {
		this.shipmentRepository.deleteById(id);
	}
	
	@Override
	public List<ShipmentEntity> findAllShipments() {
		return StreamSupport.stream(this.shipmentRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public Optional<ShipmentEntity> findShipmentById(Long id) {
		return this.shipmentRepository.findById(id);
	}
	
	@Override
	public boolean isShipmentExists(Long id) {
		return this.shipmentRepository.existsById(id);
	}





}
