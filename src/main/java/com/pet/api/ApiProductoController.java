package com.pet.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.Services.ProductoService;
import com.pet.model.Producto;

@RestController
@RequestMapping("/apirest/producto")
public class ApiProductoController {
	
	@Autowired
	private ProductoService servp;

	@PostMapping("/grabar")
	public ResponseEntity<Producto> grabar(@RequestBody Producto producto){
		
		return new ResponseEntity<>(servp.grabarProducto(producto), HttpStatus.CREATED);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Producto>> obtenerProductos(){		
		
		return new ResponseEntity<>(servp.obtenerProductos(),HttpStatus.OK);
	}
	
	@GetMapping("/{codproducto}")
	public ResponseEntity<Producto> buscar(@PathVariable Integer codproducto){
		return new ResponseEntity<>(servp.obtenerProducto(codproducto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{codproducto}")
	public ResponseEntity<Producto> eliminar(@PathVariable Integer codproducto){
		servp.eliminarProducto(codproducto);
		return new ResponseEntity<Producto>(HttpStatus.OK);
	}
	
	

}
