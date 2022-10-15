package com.pet.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.model.Producto;
import com.pet.repository.IProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private IProductoRepository repop;
	
	public List<Producto> obtenerProductos(){
		
		return repop.findAll();
	};
	
	public Producto grabarProducto(Producto p){
		
		return repop.save(p);
	};
	
	public Producto obtenerProducto(Integer codproducto) {
		
		return repop.findById(codproducto).get();
	}
	
	public void eliminarProducto(Integer codproducto){
		
		repop.deleteById(codproducto);
	};
	
	

}
