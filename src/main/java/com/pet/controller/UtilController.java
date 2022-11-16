package com.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.pet.model.Categoria;
import com.pet.model.Estado;
import com.pet.model.Tipo;
import com.pet.model.Usuario;
import com.pet.repository.ICategoriaRepository;
import com.pet.repository.IEstadoRepository;
import com.pet.repository.ITipoRepository;
import com.pet.repository.IUsuarioRepository;

@Controller
public class UtilController {
	
	@Autowired
	private IUsuarioRepository repoU;
	
	@Autowired
	private ITipoRepository repoT;
	
	@Autowired
	private ICategoriaRepository repoC;
	
	@Autowired
	private IEstadoRepository repoE;
	
	/*TIPOS  DE USUARIO*/
	/*LISTAR TIPOS Y CATEGORIAS*/
	@RequestMapping("/util/listar")
	public String listaProductos(@ModelAttribute Usuario usuario, Model model, @ModelAttribute Tipo tipo, @ModelAttribute Categoria categoria, @ModelAttribute Estado estado) {
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		model.addAttribute("lstCategorias", repoC.findAll());
		model.addAttribute("lstTipos", repoT.findAll());
		model.addAttribute("lstEstados", repoE.findAll());
		return "consulta-utils";
	}
		
	@PostMapping("/util/grabartipo")
	public RedirectView grabarTipo(@ModelAttribute Usuario usuario,@ModelAttribute Tipo tipo,@ModelAttribute Categoria categoria, Model model) {
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);	
		try {
			Tipo t = repoT.findById(tipo.getCod_tipo()).get();
			repoT.save(tipo);
			model.addAttribute("mensaje","Tipo actualizado");		
		} catch (Exception e) {
			repoT.save(tipo);
			model.addAttribute("mensaje","Tipo agregado");	
		}
		repoT.save(tipo);
		model.addAttribute("lstCategorias", repoC.findAll());
		model.addAttribute("lstTipos", repoT.findAll());	
		return new RedirectView("listar");
	}
	
	@PostMapping("/util/editarTipo")
    public String editarPag(@ModelAttribute Usuario usuario, Model model, @ModelAttribute Tipo tipo, @ModelAttribute Categoria categoria) {
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);		
		model.addAttribute("tipo", repoT.findById(tipo.getCod_tipo()));		 
		model.addAttribute("lstCategorias", repoC.findAll());
		model.addAttribute("lstTipos", repoT.findAll());    
        return "consulta-utils";
    }
	
	@PostMapping("/util/eliminarTipo")
	public RedirectView eliminarTipo(@ModelAttribute Usuario usuario,@ModelAttribute Tipo tipo ,Model model, @ModelAttribute Categoria categoria) {
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);		
		try {
			Tipo t = repoT.findById(tipo.getCod_tipo()).get();
			repoT.delete(tipo);
			model.addAttribute("mensaje","Tipo eliminado");
		} catch (Exception e) {		
			model.addAttribute("mensaje","Error al eliminar");	
		}
		model.addAttribute("lstCategorias", repoC.findAll());
		model.addAttribute("lstTipos", repoT.findAll());
		return new RedirectView("listar");
	}
	/*CATEGORIA DE PRODUCTO*/
	@PostMapping("/util/grabarCate")
	public RedirectView grabarCate(@ModelAttribute Usuario usuario,@ModelAttribute Tipo tipo,@ModelAttribute Categoria categoria, Model model) {
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		
		try {
			Categoria c = repoC.findById(categoria.getCod_cate()).get();
			repoC.save(categoria);
			model.addAttribute("mensaje","Categoria actualizada");
		} catch (Exception e) {
			repoC.save(categoria);
			model.addAttribute("mensaje","Categoria agregada");	
		}
		repoC.save(categoria);
		model.addAttribute("lstCategorias", repoC.findAll());
		model.addAttribute("lstTipos", repoT.findAll());

		return new RedirectView("listar");
	}
	
	@PostMapping("/util/editarCate")
    public String editarCate(@ModelAttribute Usuario usuario, Model model, @ModelAttribute Categoria categoria,@ModelAttribute Tipo tipo) {
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		
		model.addAttribute("categoria", repoC.findById(categoria.getCod_cate()));
		model.addAttribute("lstCategorias", repoC.findAll());
		model.addAttribute("lstTipos", repoT.findAll());   
        return "consulta-utils";
    }
	
	@PostMapping("/util/eliminarCate")
	public RedirectView eliminarCate(@ModelAttribute Usuario usuario,@ModelAttribute Categoria categoria, Model model,@ModelAttribute Tipo tipo) {
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		try {
			Categoria c = repoC.findById(categoria.getCod_cate()).get();
			repoC.delete(categoria);
			model.addAttribute("mensaje","Categoria eliminado");
		} catch (Exception e) {	
			model.addAttribute("mensaje","Error al eliminar");	
		}
		model.addAttribute("lstCategorias", repoC.findAll());
		model.addAttribute("lstTipos", repoT.findAll());	
		return new RedirectView("listar");
	}
	
	/*ESTADO*/
	@PostMapping("/util/grabarEstado")
	public RedirectView grabarEstado(@ModelAttribute Usuario usuario,@ModelAttribute Tipo tipo,@ModelAttribute Categoria categoria, Model model, @ModelAttribute Estado estado) {
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);	
		try {
			Estado e = repoE.findById(estado.getCod_est()).get();
			repoE.save(estado);
			model.addAttribute("mensaje","Estado actualizado");		
		} catch (Exception e) {
			repoE.save(estado);
			model.addAttribute("mensaje","Estado agregado");	
		}
		repoE.save(estado);
		model.addAttribute("lstCategorias", repoC.findAll());
		model.addAttribute("lstTipos", repoT.findAll());
		model.addAttribute("lstEstados", repoE.findAll());
		return new RedirectView("listar");
	}
	
	@PostMapping("/util/editarEstado")
    public String editarPag(@ModelAttribute Usuario usuario, Model model, @ModelAttribute Tipo tipo, @ModelAttribute Categoria categoria, @ModelAttribute Estado estado) {
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);		
		model.addAttribute("estado", repoE.findById(estado.getCod_est()));		 
		model.addAttribute("lstCategorias", repoC.findAll());
		model.addAttribute("lstTipos", repoT.findAll());
		model.addAttribute("lstEstados", repoE.findAll());
        return "consulta-utils";
    }
	
	@PostMapping("/util/eliminarEstado")
	public RedirectView eliminarEstado(@ModelAttribute Usuario usuario,@ModelAttribute Tipo tipo ,Model model, @ModelAttribute Categoria categoria, @ModelAttribute Estado estado) {
		usuario = repoU.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);		
		try {
			Estado e = repoE.findById(estado.getCod_est()).get();
			repoE.delete(estado);
			model.addAttribute("mensaje","Estado eliminado");
		} catch (Exception e) {		
			model.addAttribute("mensaje","Error al eliminar");	
		}
		model.addAttribute("lstCategorias", repoC.findAll());
		model.addAttribute("lstTipos", repoT.findAll());
		model.addAttribute("lstEstados", repoE.findAll());
		return new RedirectView("listar");
	}
}
