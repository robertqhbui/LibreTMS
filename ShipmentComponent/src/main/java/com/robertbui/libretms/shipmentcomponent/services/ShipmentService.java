package com.robertbui.libretms.shipmentcomponent.services;

import java.util.List;
import java.util.Optional;

import com.robertbui.libretms.shipmentcomponent.domain.entities.ShipmentEntity;

public interface ShipmentService {
	
	ShipmentEntity saveShipment(ShipmentEntity shipmentEntity);
	
	ShipmentEntity partialUpdateShipment(Long id, ShipmentEntity shipmentEntity);

	List<ShipmentEntity> findAllShipments();

	Optional<ShipmentEntity> findShipmentById(Long id);
	
	boolean isShipmentExists(Long id);

	void deleteShipment(Long id);
}
