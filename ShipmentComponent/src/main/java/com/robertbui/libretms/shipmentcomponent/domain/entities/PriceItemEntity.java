package com.robertbui.libretms.shipmentcomponent.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "priceitems")
public class PriceItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "priceitem_id_sequence")
	private Long id;

	private String priceCode;

	private Double priceAmount;

	private Double taxAmount;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "shipment_id") private ShipmentEntity shipmentEntity;
	 */

}
