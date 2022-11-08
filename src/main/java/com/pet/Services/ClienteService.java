package com.pet.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.model.DetalleVenta;
import com.pet.model.Usuario;
import com.pet.model.Venta;
import com.pet.repository.IDetalleVentaRepository;
import com.pet.repository.IUsuarioRepository;
import com.pet.repository.IVentaRepository;

@Service
public class ClienteService {
	
	@Autowired
	private IUsuarioRepository repoU;
	
	@Autowired
	private IVentaRepository repoV;
	
	@Autowired
	private IDetalleVentaRepository repoD;
	
	public Usuario obtenerUsuario(String dni) {
		
		return repoU.findByDni(dni);
	}
	
	public List<Venta> obtenerVentas(String dni){
		
		Usuario u = repoU.findByDni(dni);
		
		return repoV.findByCodusuAndEstado(u.getCodusu(), "R");
	};
	
	public List<DetalleVenta> obtenerDetalles(int codVen){
		
		return repoD.findByCodVen(codVen);
	};
	
	
	
	
}
