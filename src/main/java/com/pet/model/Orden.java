package com.pet.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="orden")
@Entity
public class Orden {
	@Id
	private Integer id;
	private String numero;
	private Date fechaCreacion;
	private Date FechaRecibida;
	private double total;
	
	
}
