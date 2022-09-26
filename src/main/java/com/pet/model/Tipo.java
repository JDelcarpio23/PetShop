package com.pet.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tipo_usuario")
@Data
public class Tipo {
	
	@Id
	private int cod_tipo;
    private String desc_tipo;

}
