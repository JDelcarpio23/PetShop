package com.pet.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.Services.ProductoService;
import com.pet.model.Producto;

@RestController
@RequestMapping("/api/producto")
public class ApiProductoController {
	
	@Autowired
	private ProductoService servp;
	
	@GetMapping()
	public ArrayList<Producto> obtenerProductos(){		
		
		return servp.obtenerProductos();
	}

}
