package com.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@PostMapping("/usuario/editarPerfil")
	public String actualizarPerfilUsuario(@ModelAttribute Usuario usuario, Model model ) {	
		System.out.println("Enviado " + usuario);
		
		try {
						
			repoU.save(usuario);
			model.addAttribute("mensaje", "Usuario Actualizado");
			
			
		} catch (Exception e) {
			
			model.addAttribute("mensaje","Error al Actualizar");				
			
		}		
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		return "perfil";
	}

}
