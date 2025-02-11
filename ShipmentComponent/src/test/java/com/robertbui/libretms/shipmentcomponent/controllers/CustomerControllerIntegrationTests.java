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
import com.robertbui.libretms.shipmentcomponent.domain.dto.CustomerDto;
import com.robertbui.libretms.shipmentcomponent.domain.entities.CustomerEntity;
import com.robertbui.libretms.shipmentcomponent.services.CustomerService;

import jakarta.transaction.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTests {
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	private CustomerService customerService;

	@Autowired
	public CustomerControllerIntegrationTests(MockMvc mockMvc, CustomerService customerService) {
		this.mockMvc = mockMvc;
		this.objectMapper = new ObjectMapper();
		this.customerService = customerService;
	}

	@Test
	public void testCreatedCustomerSuccessfullyReturnedHttp201Created() throws Exception {
		CustomerDto customerDto = TestDataUtil.buildCustomerA();
		String customerJason = this.objectMapper.writeValueAsString(customerDto);
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/customers/" + customerDto.getCode())
						.contentType(MediaType.APPLICATION_JSON).content(customerJason))
				.andExpect(MockMvcResultMatchers.status().isCreated());

	}

	@Test
	public void testCreatedCustomerSuccessfullyReturnsCreatedCustomer() throws Exception {
		CustomerDto customerDto = TestDataUtil.buildCustomerB();
		String customerJason = this.objectMapper.writeValueAsString(customerDto);
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/customers/" + customerDto.getCode())
						.contentType(MediaType.APPLICATION_JSON).content(customerJason))
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(customerDto.getCode()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(customerDto.getName()));

	}

	@Test
	public void testCreatedCustomerSuccessfullyReturnsHttpStatus200() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/customers").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testGetAllCustomersReturnsListOfCustomers() throws Exception {
		CustomerEntity customerEntityA = TestDataUtil.buildCustomerEntityC();
		this.customerService.saveCustomer(customerEntityA.getCode(), customerEntityA);
		CustomerEntity customerEntityB = TestDataUtil.buildCustomerEntityC();
		this.customerService.saveCustomer(customerEntityB.getCode(), customerEntityB);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/customers").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("YOUI"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("YOUI PTY LTD"));

	}

	@Test
	public void testGetCustomerReturnsHttpStatus404WhenExist() throws Exception {

		CustomerEntity customerEntity = TestDataUtil.buildCustomerEntityC();
		this.customerService.saveCustomer(customerEntity.getCode(), customerEntity);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/" + customerEntity.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void testUpdateBookReturnsHttpStatus200OK() throws Exception {
		
		CustomerEntity customerEntityA = TestDataUtil.buildCustomerEntityA();
		CustomerEntity customerEntitySaved = this.customerService.saveCustomer(customerEntityA.getCode(), customerEntityA);
		
		CustomerDto  customerDto = TestDataUtil.buildCustomerA();
		customerDto.setCode(customerEntitySaved.getCode());
		
		String customerJason = this.objectMapper.writeValueAsString(customerDto);
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/customers/" + customerDto.getCode())
						.contentType(MediaType.APPLICATION_JSON).content(customerJason))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void testUpdateCustomerSuccessfullyReturnsUpdatedCustomer() throws Exception {
		CustomerEntity customerEntityA = TestDataUtil.buildCustomerEntityA();
		CustomerEntity customerEntitySaved = this.customerService.saveCustomer(customerEntityA.getCode(), customerEntityA);
		
		CustomerDto  customerDto = TestDataUtil.buildCustomerA();
		customerDto.setCode(customerEntitySaved.getCode());
		
		String customerJason = this.objectMapper.writeValueAsString(customerDto);
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/customers/" + customerDto.getCode())
						.contentType(MediaType.APPLICATION_JSON).content(customerJason))
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(customerDto.getCode()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(customerDto.getName()));

	}
	
	@Test
	public void testPartialUpdateBookReturnsHttpStatus200OK() throws Exception {
		CustomerEntity customerEntityA = TestDataUtil.buildCustomerEntityA();
		CustomerEntity customerEntitySaved = this.customerService.saveCustomer(customerEntityA.getCode(), customerEntityA);
		
		CustomerDto  customerDto = TestDataUtil.buildCustomerA();
		customerDto.setCode(customerEntitySaved.getCode());
		
		customerDto.setIsActive(true);
		
		String customerJason = this.objectMapper.writeValueAsString(customerDto);
		this.mockMvc
				.perform(MockMvcRequestBuilders.patch("/customers/" + customerDto.getCode())
						.contentType(MediaType.APPLICATION_JSON).content(customerJason))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testPartialUpdateCustomerReturnsUpdatedCustomer() throws Exception {
		CustomerEntity customerEntityA = TestDataUtil.buildCustomerEntityA();
		CustomerEntity customerEntitySaved = this.customerService.saveCustomer(customerEntityA.getCode(), customerEntityA);
		
		CustomerDto  customerDto = TestDataUtil.buildCustomerA();
		customerDto.setCode(customerEntitySaved.getCode());
		
		customerDto.setIsActive(true);
		
		String customerJason = this.objectMapper.writeValueAsString(customerDto);
		this.mockMvc
				.perform(MockMvcRequestBuilders.patch("/customers/" + customerDto.getCode())
						.contentType(MediaType.APPLICATION_JSON).content(customerJason))
				.andExpect(MockMvcResultMatchers.jsonPath("$.isActive").value(customerDto.getIsActive()));
	}
	
	@Test
	public void testDeleteCustomerReturnsHttpStatus404ForNonExistingCustomer() throws Exception {
			
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/customers/000")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void testDeleteCustomerReturnsHttpStatus204ForExistingCustomer() throws Exception {
		CustomerEntity customerEntityA = TestDataUtil.buildCustomerEntityA();
		CustomerEntity customerEntitySaved = this.customerService.saveCustomer(customerEntityA.getCode(), customerEntityA);
			
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/customers/" + customerEntitySaved.getCode())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
