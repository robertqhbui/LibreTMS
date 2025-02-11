package com.robertbui.libretms.shipmentcomponent.domain.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shipments")
public class ShipmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_id_sequence")
	private Long id;

	private Timestamp readyTimestamp;

	private Timestamp scheduledPickupTimestamp;

	private Timestamp scheduledDeliveryTimestamp;

	private Timestamp latestCancelTimestamp;

	private String deliveryInstruction;

	private Double actualShippingCost;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;
	
	/*
	 * @OneToMany(mappedBy = "shipment", cascade = CascadeType.PERSIST, fetch =
	 * FetchType.EAGER) private List<ShipItemEntity> shipItemEntities;
	 * 
	 * @OneToMany(mappedBy = "shipment", cascade = CascadeType.PERSIST, fetch =
	 * FetchType.EAGER) private List<PriceItemEntity> priceItemEntities;
	 * 
	 * @OneToMany(mappedBy = "shipment", cascade = CascadeType.PERSIST, fetch =
	 * FetchType.EAGER) private List<ShipmentLegEntity> shipmentLegEntities;
	 */
}
