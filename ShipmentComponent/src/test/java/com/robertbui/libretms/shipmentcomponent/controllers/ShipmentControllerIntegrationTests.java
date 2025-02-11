package com.robertbui.libretms.shipmentcomponent.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robertbui.libretms.shipmentcomponent.TestDataUtil;
import com.robertbui.libretms.shipmentcomponent.domain.entities.ShipmentEntity;
import com.robertbui.libretms.shipmentcomponent.services.ShipmentService;

import jakarta.transaction.Transactional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@AutoConfigureMockMvc
public class ShipmentControllerIntegrationTests {

	private ObjectMapper objectMapper;
	private ShipmentService shipmentService;
	private MockMvc mockMvc;

	@Autowired
	public ShipmentControllerIntegrationTests(MockMvc mockMvc, ShipmentService shipmentService) {
		this.mockMvc = mockMvc;
		this.objectMapper = new ObjectMapper();
		this.shipmentService = shipmentService;
	}
	
	@Test
	public void testCreatedShipmentSuccessfullyReturnedHttp201Created() throws Exception
	{
		ShipmentEntity shipmentEntityA = TestDataUtil.buildShipmentA();
		shipmentEntityA.setId(null);
		String shipmentJason = this.objectMapper.writeValueAsString(shipmentEntityA);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/shipments")
				.contentType(MediaType.APPLICATION_JSON)
				.content(shipmentJason)
				).andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void testCreatedShipmentSuccessfullyReturnsSavedShipment() throws Exception
	{
		ShipmentEntity shipmentEntityB = TestDataUtil.buildShipmentB();
		shipmentEntityB.setId(null);
		String shipmentJason = this.objectMapper.writeValueAsString(shipmentEntityB);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/shipments")
				.contentType(MediaType.APPLICATION_JSON)
				.content(shipmentJason)
				).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber()
				).andExpect(MockMvcResultMatchers.jsonPath("$.deliveryInstruction").value("B SPECIAL INSTRUCTION")
				).andExpect(MockMvcResultMatchers.jsonPath("$.actualShippingCost").value(2.00)
				);
	}
	
	@Test
	public void testGetAllShipmentsReturnsListOfShipments() throws Exception
	{
		ShipmentEntity shipmentEntityA = TestDataUtil.buildShipmentA();
		this.shipmentService.saveShipment(shipmentEntityA);
		
		ShipmentEntity shipmentEntityB = TestDataUtil.buildShipmentB();
		this.shipmentService.saveShipment(shipmentEntityB);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/shipments")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
				).andExpect(MockMvcResultMatchers.jsonPath("$[0].deliveryInstruction").value("A SPECIAL INSTRUCTION")
				).andExpect(MockMvcResultMatchers.jsonPath("$[0].actualShippingCost").value(1.00)
				);
	}
	
	@Test
	public void testGetShipmentReturnsHttpStatus404WhenExist() throws Exception {

		ShipmentEntity shipmentEntity = TestDataUtil.buildShipmentA();
		this.shipmentService.saveShipment(shipmentEntity);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/shipments/" + shipmentEntity.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void testUpdateShipmentReturnsUpdatedShipment() throws Exception {

		ShipmentEntity shipmentEntity = TestDataUtil.buildShipmentA();
		ShipmentEntity savedShipmentEntity = this.shipmentService.saveShipment(shipmentEntity);
		
		savedShipmentEntity.setActualShippingCost(99.00);
		
		String shipmentJason = this.objectMapper.writeValueAsString(savedShipmentEntity);

		this.mockMvc.perform(MockMvcRequestBuilders.put("/shipments/" + savedShipmentEntity.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(shipmentJason)
				).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber()
				).andExpect(MockMvcResultMatchers.jsonPath("$.deliveryInstruction").value("A SPECIAL INSTRUCTION")
				).andExpect(MockMvcResultMatchers.jsonPath("$.actualShippingCost").value(99.00)
				);
	}
	
	@Test
	public void testPartialUpdateShipmentReturnsUpdatedShipment() throws Exception {

		ShipmentEntity shipmentEntity = TestDataUtil.buildShipmentA();
		ShipmentEntity savedShipmentEntity = this.shipmentService.saveShipment(shipmentEntity);
		
		savedShipmentEntity.setActualShippingCost(99.00);
		
		String shipmentJason = this.objectMapper.writeValueAsString(savedShipmentEntity);

		this.mockMvc.perform(MockMvcRequestBuilders.patch("/shipments/" + savedShipmentEntity.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(shipmentJason)
				).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber()
				).andExpect(MockMvcResultMatchers.jsonPath("$.deliveryInstruction").value("A SPECIAL INSTRUCTION")
				).andExpect(MockMvcResultMatchers.jsonPath("$.actualShippingCost").value(99.00)
				);
	}
	
	@Test
	public void testDeleteShipmentReturnsHttpStatus404ForNonExistingShipment()
			throws Exception {
		
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/shipments/000")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void testDeleteShipmentReturnsHttpStatus204ForExistingShipment()
			throws Exception {
		ShipmentEntity shipmentEntity = TestDataUtil.buildShipmentA();
		ShipmentEntity savedShipmentEntity = this.shipmentService.saveShipment(shipmentEntity);
		
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/shipments/" + savedShipmentEntity.getId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
