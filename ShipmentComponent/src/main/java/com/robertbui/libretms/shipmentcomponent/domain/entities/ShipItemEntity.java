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
@Table(name = "shipitems")
public class ShipItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipitem_id_sequence")
	private Long id;

	private Integer declaredWidth;

	private Integer declaredHeight;

	private Integer declaredLength;

	private Double declaredCubedWeight;

	private Double declaredDeadWeight;

	private Integer actualWidth;

	private Integer actualHeight;

	private Integer actualLength;

	private Double actualCubedWeight;

	private Double actualDeadWeight;

	private Double chargedWeight;

	private String description;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "shipment_id") private ShipmentEntity shipmentEntity;
	 */

}
