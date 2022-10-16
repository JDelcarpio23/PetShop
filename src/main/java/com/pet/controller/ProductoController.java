package com.pet.controller;

import com.pet.model.Producto;
import com.pet.model.Usuario;
import com.pet.repository.ICategoriaRepository;
import com.pet.repository.IProductoRepository;
import com.pet.repository.IUsuarioRepository;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ProductoController {


	
	@Autowired
	private IProductoRepository repop;
	
	@Autowired
	private ICategoriaRepository repoc;
	
	@Autowired
	private IUsuarioRepository repou;
	
	
	
	@GetMapping("/producto/listar")
	public String listaProductos(@ModelAttribute Usuario usuario, Model model) {
		model.addAttribute("lstProductos", repop.findAll());
		
		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		return "consulta-producto";
	}
	
	/*CATALOGO*/
	@GetMapping("/producto/catalogo")
	public String catalogo(@ModelAttribute Usuario usuario, Model model) {
		model.addAttribute("lstProductos", repop.findAll());
		
		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		return "catalogo";
	}
	
	/*CATALOGO INICIO*/
	@GetMapping("/producto/catalogo-inicio")
	public String catalogoInicio(Model model) {
		model.addAttribute("lstProductos", repop.findAll());
		return "catalogo-inicio";
	}
	
	@GetMapping("/producto/cargar")
	public String abrirPag(@ModelAttribute Usuario usuario, Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("lstCategorias", repoc.findAll());
		
		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		return "crudProductos";
	}
	
	@PostMapping("/producto/grabar")
	public String grabarPag(@ModelAttribute Usuario usuario,@ModelAttribute Producto producto, Model model, @RequestParam("file")MultipartFile imagen) {
		System.out.println(producto);
		if(!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static/productos");
			String rutaAbsoluta= directorioImagenes.toFile().getAbsolutePath();
			try {
				byte[]bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta+"//"+imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				
				producto.setImg(imagen.getOriginalFilename());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			producto = repop.findById(producto.getCod_prod()).get();
			repop.save(producto);
			model.addAttribute("mensaje", "Producto editado con exito");
		} catch (Exception e) {
			repop.save(producto);
			model.addAttribute("mensaje","Producto Registrado");
		}
		
		model.addAttribute("lstCategorias",repoc.findAll());
		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		return "crudProductos";
	}
	
	@PostMapping("/producto/editar")
	public String editarPag(@ModelAttribute Usuario usuario,@ModelAttribute Producto p, Model model) {
		model.addAttribute("producto", repop.findById(p.getCod_prod()));
		model.addAttribute("lstCategorias",repoc.findAll());
		
		model.addAttribute("lstCategorias",repoc.findAll());
		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		return "crudProductos";
	}
	@PostMapping("/producto/eliminar")
	public String eliminarPag(@ModelAttribute Usuario usuario,@ModelAttribute Producto p, Model model) {
System.out.println(p);
		
		p = repop.findById(p.getCod_prod()).get();
		repop.delete(p);
		model.addAttribute("mensaje","Producto Eliminado");
		//repoU.save(u);
		
		model.addAttribute("lstProductos",repop.findAll());
		
		model.addAttribute("lstCategorias",repoc.findAll());
		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		return "consulta-producto";
		 
	}
	@GetMapping("/producto/detalle/{id}")
	public String detalleProducto(@ModelAttribute Producto p, Model model,@PathVariable("id")Long cod_prod) {
		model.addAttribute("producto", repop.findById(p.getCod_prod()));
		model.addAttribute("lstCategorias",repoc.findAll());
		
		
		return "detalleProducto";
	}
	
	/*CONTROLADOR PARA DETALLE PRODUCTO - FREDY*/
	@PostMapping("/producto/detalles")
	public String detalleProd(@ModelAttribute Usuario usuario,@ModelAttribute Producto p, Model model) {
		
		com.pet.util.Constantes.CODIGOPROD = p.getCod_prod();
		
		p = repop.findById(com.pet.util.Constantes.CODIGOPROD).get();
		
		model.addAttribute("producto", p );
		model.addAttribute("lstCategorias",repoc.findAll());
		
		model.addAttribute("lstCategorias",repoc.findAll());
		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		
		
		return "detalleProducto";
	}
	
	
}
