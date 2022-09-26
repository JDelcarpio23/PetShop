package com.pet.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="estado")
@Entity
public class Estado {
	@Id
	private int cod_est;
	private String desc_est;
}
