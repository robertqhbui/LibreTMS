package com.robertbui.libretms.shipmentcomponent;

import java.util.Optional;

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
import com.robertbui.libretms.shipmentcomponent.controllers.CustomerController;
import com.robertbui.libretms.shipmentcomponent.controllers.ShipmentController;
import com.robertbui.libretms.shipmentcomponent.domain.dto.CustomerDto;
import com.robertbui.libretms.shipmentcomponent.domain.entities.CustomerEntity;
import com.robertbui.libretms.shipmentcomponent.domain.entities.ShipmentEntity;
import com.robertbui.libretms.shipmentcomponent.services.CustomerService;
import com.robertbui.libretms.shipmentcomponent.services.ShipmentService;

import jakarta.transaction.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@AutoConfigureMockMvc

public class ShipmentComponentIntegrationTest {
	private CustomerService customerService;
	private ObjectMapper objectMapper;
	private ShipmentService shipmentService;
	private MockMvc mockMvc;

	@Autowired
	public ShipmentComponentIntegrationTest(MockMvc mockMvc, ShipmentService shipmentService,
			CustomerService customerService) {
		this.mockMvc = mockMvc;
		this.objectMapper = new ObjectMapper();
		this.shipmentService = shipmentService;
		this.customerService = customerService;
	}

	@Test
	public void testCreateShipmentForACustomerShipmentThenRecall() throws Exception {
		CustomerEntity customerEntityA = TestDataUtil.buildCustomerEntityA();
		CustomerEntity customerEntitySaved = this.customerService.saveCustomer(customerEntityA.getCode(), customerEntityA);
		
		ShipmentEntity shipmentEntityA = TestDataUtil.buildShipmentC(customerEntitySaved);
		
		shipmentEntityA.setId(null);
		String shipmentJason = this.objectMapper.writeValueAsString(shipmentEntityA);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/shipments")
				.contentType(MediaType.APPLICATION_JSON)
				.content(shipmentJason)
				).andExpect(MockMvcResultMatchers.status().isCreated());	
		
	}
}
