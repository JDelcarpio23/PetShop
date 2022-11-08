package com.pet.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.Services.ClienteService;
import com.pet.model.DetalleVenta;
import com.pet.model.Usuario;
import com.pet.model.Venta;

@RestController
@RequestMapping("/apirest/cliente")
public class ApiUsuarioController {
	
	@Autowired
	private ClienteService servC;

	@GetMapping("/{dni}")
	public ResponseEntity<Usuario> buscar(@PathVariable String dni){
		
		return new ResponseEntity<>(servC.obtenerUsuario(dni),HttpStatus.OK);
	}
	
	@GetMapping("/compras/{dni}")
	public ResponseEntity<List<Venta>> obtenerVenta(@PathVariable String dni){
		
		return new ResponseEntity<>(servC.obtenerVentas(dni),HttpStatus.OK);
	}
	
	@GetMapping("/detalle/{codVen}")
	public ResponseEntity<List<DetalleVenta>> Detalles(@PathVariable Integer codVen){
		
		System.out.println("el codigo de venta es " + codVen);
		
		return new ResponseEntity<>(servC.obtenerDetalles(codVen),HttpStatus.OK);
	}
	

}
