package com.pet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="detalle_venta")
@Entity
public class DetalleOrden {

	@Id
	private Integer cod_detalle;
	@Column(name="cod_ven", nullable = false)
	private Integer codven;
	private int cod_prod;	
    private Integer cantidad;
    private double total;
}
