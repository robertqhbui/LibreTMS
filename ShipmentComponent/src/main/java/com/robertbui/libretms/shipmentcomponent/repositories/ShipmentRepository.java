package com.robertbui.libretms.shipmentcomponent.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.robertbui.libretms.shipmentcomponent.domain.entities.ShipmentEntity;

@Repository
public interface ShipmentRepository extends CrudRepository<ShipmentEntity, Long> {

}
