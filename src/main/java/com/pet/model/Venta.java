package com.pet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="venta")
@Data
public class Venta {
	@Id
	@Column(name = "cod_ven", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codven;
	
	@Column(name="cod_usu")
    private String codusu;
	private String cod_pago;
    private String fecha_bol;
    private double prec_total;
    private String estado;
    
    
	
	public Integer getCodVen() {
		return codven;
	}
	public void setCodVen(Integer cod_ven) {
		this.codven = cod_ven;
	}
	public String getCodusu() {
		return codusu;
	}
	public void setCodusu(String cod_usu) {
		this.codusu = cod_usu;
	}
	public String getCod_pago() {
		return cod_pago;
	}
	public void setCod_pago(String cod_pago) {
		this.cod_pago = cod_pago;
	}
	public String getFecha_bol() {
		return fecha_bol;
	}
	public void setFecha_bol(String fecha_bol) {
		this.fecha_bol = fecha_bol;
	}
	public double getPrec_total() {
		return prec_total;
	}
	public void setPrec_total(double prec_total) {
		this.prec_total = prec_total;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    
    
    

}
