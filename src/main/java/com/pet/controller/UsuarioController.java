package com.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pet.model.Usuario;
import com.pet.repository.IProductoRepository;
import com.pet.repository.ITipoRepository;
import com.pet.repository.IUsuarioRepository;

@Controller
public class UsuarioController {
	@Autowired
	private IProductoRepository repop;
	
	@Autowired
	private IUsuarioRepository repoU;
	
	@Autowired
	private ITipoRepository repoT;
	
	@GetMapping("/login/cargar")
	public String abrirLogin( Model model) {
		model.addAttribute("usuario", new Usuario());
		
		com.pet.util.Constantes.CODIGO = "";
		com.pet.util.Constantes.TIPO = 0;
		
		return "login";
	}
	
	@PostMapping("/login/validar")
	public String validarLogin(@ModelAttribute Usuario usuario, Model model/*, @ModelAttribute HttpSession sesion */) {	
		System.out.println("Enviado " + usuario);
		
		Usuario u = repoU.findByUsuarioAndContrasenia(usuario.getUsuario(), usuario.getContrasenia());
		System.out.println(u);
		
		if(u==null) {
			model.addAttribute("mensaje", "Usuario o Clave Incorrecto");
			return "login";
		}
		model.addAttribute("lstProductos", repop.findAll());			
		
		  com.pet.util.Constantes.CODIGO = u.getCodusu();
		  com.pet.util.Constantes.TIPO = u.getCod_tipo();
		  System.out.println("prueba " + com.pet.util.Constantes.CODIGO + "Tipo" + com.pet.util.Constantes.TIPO );
		 
		
		model.addAttribute("usuario", u);
		
		model.addAttribute("mensaje", "");
		
		return "principal";
	}
	
	/*RETORNAR AL INICIO*/
	
	@GetMapping("/inicio/retornar")
	public String retornarInicio(@ModelAttribute Usuario usuario, Model model ) {	
		model.addAttribute("lstProductos", repop.findAll());
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		usuario = repoU.findById(usuario.getCodusu()).get();
		System.out.println(usuario);
		model.addAttribute("usuario", usuario);
		
		return "principal";
	}
	/*RETORNAR AL INDEX*/
	
	@GetMapping("/inicio/retornar-index")
	public String retornarIndex(@ModelAttribute Usuario usuario, Model model ) {	
		
		
		return "Index";
	}
	/*MOSTRAR SEDES*/
	@GetMapping("/inicio/sedes")
	public String sedes(@ModelAttribute Usuario usuario, Model model ) {	
		
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		
		return "sedes";
	}
	
	/*MOSTRAR SEDES INICIO*/
	@GetMapping("/inicio/sedes-inicio")
	public String sedesInicio(@ModelAttribute Usuario usuario, Model model ) {	
		
		
		return "sedes-inicio";
	}
	
	
	@GetMapping("/usuario/cargar")
	public String abrirRegistro( Model model) {
		model.addAttribute("usuario", new Usuario());
		
		return "registro";
	}
	
	@PostMapping("/usuario/grabar")
	public String grabarRegistro(@ModelAttribute Usuario usuario, Model model ) {	
		System.out.println("Enviado " + usuario);
		
		try {
			usuario.setCodusu(usuario.getDni_usu());
			usuario.setCod_tipo(2);
			repoU.save(usuario);			
			model.addAttribute("mensaje", "Usuario Registrado");
			
			
		} catch (Exception e) {
			
			model.addAttribute("mensaje","Error al Registrar");				
			
		}		
		
		return "login";
	}
	
	@GetMapping("/usuario/listar")
	public String listaUsuarios(@ModelAttribute Usuario usuario, Model model) {
		
		if (com.pet.util.Constantes.TIPO == 1){
		model.addAttribute("lstUsuarios", repoU.findAll());

		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		return "listadoUsuarios";
		}else {
			return "login";
		}
		
	}
	
	@GetMapping("/mantenimiento/usuario")
	public String abrirCrud(@ModelAttribute Usuario usuario, Model model) {		
			
			model.addAttribute("usuario", new Usuario());

			model.addAttribute("lstTipos",repoT.findAll());
			
			usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
			model.addAttribute("usuario", usuario);
			
			return "crudUsuarios";		
		
	}
	
	@PostMapping("/usuario/actualizar")
	public String editarCrud(@ModelAttribute Usuario usuario, Model model ) {	
		System.out.println(usuario);
		
		usuario = repoU.findById(usuario.getCodusu()).get();
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("lstTipos",repoT.findAll());

		//usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		//model.addAttribute("usuario", usuario);
		return "crudUsuarios";
	}
	
	@PostMapping("/usuario/editar")
	public String actualizarRegistro(@ModelAttribute Usuario usuario, Model model ) {	
		System.out.println("Enviado " + usuario);
		
		try {
						
			repoU.save(usuario);
			model.addAttribute("mensaje", "Usuario Actualizado");
			
			
		} catch (Exception e) {
			
			model.addAttribute("mensaje","Error al Actualizar");				
			
		}		
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		return "crudUsuarios";
	}
	
	@PostMapping("/Usuario/eliminar")
	public String eliminarUsuario(@ModelAttribute Usuario u, Model model ) {
		
System.out.println(u);
		
		u = repoU.findById(u.getCodusu()).get();
		repoU.delete(u);
		model.addAttribute("mensaje","Usuario Eliminado");
		//repoU.save(u);
		
		model.addAttribute("lstTipos",repoT.findAll());
		model.addAttribute("lstUsuarios",repoU.findAll());
		
		u = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", u);
		return "listadoUsuarios";
	}
	
	/*CONTROLADORES MANTENIMIENTO DE USUARIO CREAR*/
	@GetMapping("/usuario/registrar")
	public String Registro(@ModelAttribute Usuario u, Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("lstTipos",repoT.findAll());
		u = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", u);
		return "registrolog";
	}
	
	@PostMapping("/usuario/guardar")
	public String registrar(@ModelAttribute Usuario usuario, Model model ) {	
		
		try {
			usuario.setCodusu(usuario.getNom_usu().substring(0,2)+usuario.getApel_usu().substring(0, 2)+usuario.getDni_usu());
			repoU.save(usuario);			
			model.addAttribute("mensaje", "Usuario Registrado");			
			
		} catch (Exception e) {
			
			model.addAttribute("mensaje","Error al Registrar");				
			
		}
		System.out.println("Enviado " + usuario);
		model.addAttribute("lstUsuarios",repoU.findAll());
		
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		
		return "listadoUsuarios";
	}
	
	
}
