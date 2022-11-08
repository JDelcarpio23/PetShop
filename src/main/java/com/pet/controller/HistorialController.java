package com.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pet.model.DetalleVenta;
import com.pet.model.Usuario;
import com.pet.model.Venta;
import com.pet.repository.IDetalleVentaRepository;
import com.pet.repository.IUsuarioRepository;
import com.pet.repository.IVentaRepository;

@Controller
public class HistorialController {
	
	@Autowired
	private IUsuarioRepository repoU;
	
	@Autowired
	private IVentaRepository repoV;
	
	@Autowired
	private IDetalleVentaRepository repoD;
	
	
	@GetMapping("/historial")
	public String listarVentas(@ModelAttribute Venta venta, Model model, @ModelAttribute Usuario usuario) {
		
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		
		model.addAttribute("lstVenta", repoV.findByCodusuAndEstado(usuario.getCodusu(), "R"));
		
		return "historial";
		
	}
	
	@PostMapping("/historial/detalles")
	public String listarDetalles(@ModelAttribute Venta venta, Model model, @ModelAttribute Usuario usuario, @ModelAttribute DetalleVenta detalleVenta) {
		
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		
		//venta = repoV.findById(venta.getCodVen()).get();
		System.out.println("codigo de venta " + venta.getCodVen());
		
		model.addAttribute("lstDetalleVenta", repoD.findByCodVen(venta.getCodVen()));
		
		return "historialDetalle";
		
	}

}
