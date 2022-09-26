package com.pet.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="categoria")
@Entity
public class Categoria {
	@Id
	private int cod_cate;
	private String desc_cate;
}
