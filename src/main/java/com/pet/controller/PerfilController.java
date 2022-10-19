package com.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.pet.model.Usuario;
import com.pet.repository.IUsuarioRepository;

@Controller
public class PerfilController {
	
	@Autowired
	private IUsuarioRepository repoU;
	
	@GetMapping("/perfil/cargar")
	public String abrirPerfil( @ModelAttribute Usuario usuario, Model model) {
		
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		
		model.addAttribute("usuario", usuario);		
				
		return "perfil";
	}

}
