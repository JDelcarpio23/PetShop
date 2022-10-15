package com.pet.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="detalle_orden")
@Entity
public class DetalleOrden {
	@Id
	private Integer id;
	private String nombre;
	private double cantidad;
	private double precio;
	private double total;
	
}
