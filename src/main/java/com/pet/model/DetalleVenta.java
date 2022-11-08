package com.pet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="detalle_venta")
@Entity
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cod_detalle;
	
	@Column(name="cod_ven", nullable = false)
	private Integer codVen;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cod_prod")
	private Producto producto;    

	
    private Integer cantidad;
    private double total;
    
    
	
	
	
    
}
