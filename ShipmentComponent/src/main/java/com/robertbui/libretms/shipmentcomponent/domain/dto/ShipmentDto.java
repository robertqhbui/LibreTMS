package com.robertbui.libretms.shipmentcomponent.domain.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDto {
	private Long id;

	private Timestamp readyTimestamp;

	private Timestamp scheduledPickupTimestamp;

	private Timestamp scheduledDeliveryTimestamp;

	private Timestamp latestCancelTimestamp;

	private String deliveryInstruction;

	private Double actualShippingCost;

	private CustomerDto customer;
	
	/*
	 * private List<ShipItem> shipItems;
	 * 
	 * private List<PriceItem> priceItems;
	 * 
	 * private List<ShipmentLeg> shipmentLegs;
	 */

}
