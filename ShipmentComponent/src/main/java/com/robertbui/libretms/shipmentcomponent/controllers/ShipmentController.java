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

import com.robertbui.libretms.shipmentcomponent.domain.dto.ShipmentDto;
import com.robertbui.libretms.shipmentcomponent.domain.entities.ShipmentEntity;
import com.robertbui.libretms.shipmentcomponent.mappers.Mapper;
import com.robertbui.libretms.shipmentcomponent.services.ShipmentService;

import lombok.extern.java.Log;

@RestController
@Log
public class ShipmentController {

	private ShipmentService shipmentService;

	private Mapper<ShipmentEntity, ShipmentDto> shipmentMapper;

	public ShipmentController(ShipmentService shipmentService, Mapper<ShipmentEntity, ShipmentDto> shipmentMapper) {
		this.shipmentService = shipmentService;
		this.shipmentMapper = shipmentMapper;
	}

	@PostMapping(path = "/shipments")
	public ResponseEntity<ShipmentDto> createShipment(@RequestBody final ShipmentDto shipmentDto) {
		ShipmentEntity shipmentEntity = this.shipmentMapper.mapFrom(shipmentDto);
		ShipmentEntity savedShipmentEntity = this.shipmentService.saveShipment(shipmentEntity);
		return new ResponseEntity<>(this.shipmentMapper.mapTo(savedShipmentEntity), HttpStatus.CREATED);
	}

	@PutMapping(path = "/shipments/{id}")
	public ResponseEntity<ShipmentDto> updateShipment(@PathVariable("id") Long id,
			@RequestBody final ShipmentDto shipmentDto) {
		if (!this.shipmentService.isShipmentExists(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ShipmentEntity shipmentEntity = this.shipmentMapper.mapFrom(shipmentDto);
		shipmentEntity.setId(id);
		ShipmentEntity savedShipmentEntity = this.shipmentService.saveShipment(shipmentEntity);
		return new ResponseEntity<>(this.shipmentMapper.mapTo(savedShipmentEntity), HttpStatus.OK);
	}

	@PatchMapping(path = "/shipments/{id}")
	public ResponseEntity<ShipmentDto> partialUpdateShipmenmt(@PathVariable("id") Long id,
			@RequestBody final ShipmentDto shipmentDto) {

		if (!this.shipmentService.isShipmentExists(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ShipmentEntity shipmentEntity = this.shipmentMapper.mapFrom(shipmentDto);
		ShipmentEntity savedShipmentEntity = this.shipmentService.partialUpdateShipment(id, shipmentEntity);
		return new ResponseEntity<>(this.shipmentMapper.mapTo(savedShipmentEntity), HttpStatus.OK);
	}

	@DeleteMapping(path = "/shipments/{id}")
	public ResponseEntity deleteShipment(@PathVariable("id") Long id)
	{
		if (!this.shipmentService.isShipmentExists(id))
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
		this.shipmentService.deleteShipment(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/shipments")
	public List<ShipmentDto> getShipments() {
		List<ShipmentEntity> shipments = this.shipmentService.findAllShipments();
		return shipments.stream().map(this.shipmentMapper::mapTo).collect(Collectors.toList());
	}

	@GetMapping(path = "/shipments/{id}")
	public ResponseEntity<ShipmentDto> getShipment(@PathVariable("id") Long id) {
		Optional<ShipmentEntity> shipmentFound = this.shipmentService.findShipmentById(id);
		return shipmentFound.map(shipmentEntity -> {
			ShipmentDto shipmentDto = this.shipmentMapper.mapTo(shipmentEntity);
			return new ResponseEntity<>(shipmentDto, HttpStatus.OK);
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
