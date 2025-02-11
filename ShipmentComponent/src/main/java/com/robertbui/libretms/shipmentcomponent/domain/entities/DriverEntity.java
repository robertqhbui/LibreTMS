package com.robertbui.libretms.shipmentcomponent.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="drivers")
public class DriverEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_id_sequence")
	private Long id;
	
	private String name;
	
	private String code;

}
