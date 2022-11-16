package com.pet.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tipo_usuario")
@Data
public class Tipo implements Serializable{
	private static final long serialVersionUID = 1l;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod_tipo;
	
    private String desc_tipo;

}
