package com.pet.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="producto")
@Entity
public class Producto {
	@Id
	private int cod_prod;
	
	@ManyToOne
	@JoinColumn(name="cod_cate", insertable = false, updatable = false)
	private Categoria categoria;
	private int cod_cate;
	
	private String desc_prod;
	private double precio;
	private int stock;
	
	@ManyToOne
	@JoinColumn(name="cod_est", insertable = false, updatable = false)
	private Estado estado;
	private int cod_est;
	
	private String img;
}
