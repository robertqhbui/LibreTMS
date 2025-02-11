package com.robertbui.libretms.shipmentcomponent;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.robertbui.libretms.shipmentcomponent.domain.dto.CustomerDto;
import com.robertbui.libretms.shipmentcomponent.domain.entities.CustomerEntity;
import com.robertbui.libretms.shipmentcomponent.domain.entities.PriceItemEntity;
import com.robertbui.libretms.shipmentcomponent.domain.entities.ShipItemEntity;
import com.robertbui.libretms.shipmentcomponent.domain.entities.ShipmentEntity;

public final class TestDataUtil {
	private TestDataUtil() {

	}
	
	public static ShipmentEntity buildShipmentA() {
		
		long now = System.currentTimeMillis();
		Timestamp currentTimestamp = new Timestamp(now);
		
		return ShipmentEntity.builder()
						.readyTimestamp(currentTimestamp)
						.scheduledPickupTimestamp(currentTimestamp)
						.scheduledDeliveryTimestamp(currentTimestamp)
						.latestCancelTimestamp(currentTimestamp)
						.deliveryInstruction("A SPECIAL INSTRUCTION")
						.actualShippingCost(1.00)
						.build();
	}
	
	public static ShipmentEntity buildShipmentB() {
		
		long now = System.currentTimeMillis();
		Timestamp currentTimestamp = new Timestamp(now);
		
		return ShipmentEntity.builder()
						.readyTimestamp(currentTimestamp)
						.scheduledPickupTimestamp(currentTimestamp)
						.scheduledDeliveryTimestamp(currentTimestamp)
						.latestCancelTimestamp(currentTimestamp)
						.deliveryInstruction("B SPECIAL INSTRUCTION")
						.actualShippingCost(2.00)
						.build();
	}
	public static ShipmentEntity buildShipmentC(CustomerEntity customerEntity) {
		
		long now = System.currentTimeMillis();
		Timestamp currentTimestamp = new Timestamp(now);
		
		return ShipmentEntity.builder()
						.readyTimestamp(currentTimestamp)
						.scheduledPickupTimestamp(currentTimestamp)
						.scheduledDeliveryTimestamp(currentTimestamp)
						.latestCancelTimestamp(currentTimestamp)
						.deliveryInstruction("A SPECIAL INSTRUCTION")
						.actualShippingCost(1.00)
						.customer(customerEntity)
						.build();
	}
	
	public static CustomerEntity buildCustomerEntityA() {
		return CustomerEntity.builder()
						.code("AMP")
						.name("AMP LTD")
						.build();
	}
	public static CustomerEntity buildCustomerEntityC() {
		return CustomerEntity.builder()
						.code("YOUI")
						.name("YOUI PTY LTD")
						.build();
	}
	
	public static CustomerDto buildCustomerA() {
		return CustomerDto.builder()
						.code("AMP")
						.name("AMP LTD")
						.build();
	}
	public static CustomerDto buildCustomerB() {
		return CustomerDto.builder()
						.code("BHP")
						.name("BHP PTY LTD")
						.build();
	}

	
	
	public static CustomerEntity buildCustomer(String code, String name) {
		return CustomerEntity.builder()
						.code(code)
						.name(name)
						.build();
	}
}
