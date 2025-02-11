package com.robertbui.libretms.shipmentcomponent.domain.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence_generator")
	@SequenceGenerator(name = "customer_sequence_generator", sequenceName = "customer_id_sequence", allocationSize = 1)
	private Long id;

	private String name;

	private String code;
	
	@ColumnDefault("false")
	private Boolean isActive;
	
	/*
	 * @OneToMany
	 * 
	 * @JoinColumn(name = "shipment_id") private List<Shipment> shipments;
	 */

}
