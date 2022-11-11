package com.pet.controller;

import com.pet.model.DetalleVenta;
import com.pet.model.Producto;
import com.pet.model.Usuario;
import com.pet.model.Venta;
import com.pet.repository.ICategoriaRepository;
import com.pet.repository.IDetalleVentaRepository;
import com.pet.repository.IProductoRepository;
import com.pet.repository.IUsuarioRepository;
import com.pet.repository.IVentaRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@Scope("session")
public class ProductoController {

	@Autowired
	private IProductoRepository repop;

	@Autowired
	private ICategoriaRepository repoc;

	@Autowired
	private IUsuarioRepository repou;

	@Autowired
	private IDetalleVentaRepository repodetvent;

	@Autowired
	private IVentaRepository repovent;
	
	
	
	@GetMapping("/producto/listar")
	public String listaProductos(@ModelAttribute Usuario usuario, Model model) {
		model.addAttribute("lstProductos", repop.findAll());
		
		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		return "consulta-producto";
	}
	
	/*CATALOGO*/
	@GetMapping("/producto/catalogo")
	public String catalogo(@ModelAttribute Usuario usuario, @ModelAttribute Producto producto,Model model) {
		
		com.pet.util.Constantes.CODIGOPROD = 1;
		
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
	
	/*@PostMapping("/producto/carrito")
	public String agregarCarrito(@ModelAttribute Usuario usuario,@ModelAttribute Producto p, Model model) {
		//com.pet.util.Constantes.CODIGOPROD = p.getCod_prod();
		//p = repop.findById(com.pet.util.Constantes.CODIGOPROD).get();
		p = repop.findById(p.getCod_prod()).get();
		
		model.addAttribute("producto", p );
		model.addAttribute("lstCategorias",repoc.findAll());
		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		
		System.out.println(p.getCod_prod() + " - " + p.getPrecio() + " - " + p.getDesc_prod() + " - " + p.getStock());
		return "detalleProducto";
	}*/
	
	@PostMapping("/producto/agregarcarrito")
	public String agregarCarrito(@ModelAttribute Usuario usuario, @ModelAttribute Producto p,
			@RequestParam Integer inputCantidad, Model model, @ModelAttribute Venta venta) {

		com.pet.util.Constantes.CODIGOPROD = p.getCod_prod();
		p = repop.findById(com.pet.util.Constantes.CODIGOPROD).get();

		if (inputCantidad <= p.getStock()) {

			//reducir stock
			System.out.println(p.getStock()-inputCantidad);
			p.setStock(p.getStock()-inputCantidad);
			
			// OBTENEMOS LAS VENTAS
			
			//List<Venta> lstVentaBD = repovent.findByEstado("P");
			
			List<Venta> lstVentaBD = repovent.findByCodusuAndEstado(usuario.getCodusu(), "P");
			//Venta objVenta = lista.get(0);
			
			List<DetalleVenta> lstDetalleVentaBD = new ArrayList<DetalleVenta>();
			Venta ventaBD = null;
			// Si obtenemos la venta de BD obtenemos sus detalles
			if (lstVentaBD != null && lstVentaBD.size() > 0) {
				ventaBD = lstVentaBD.get(0);
				ventaBD.setPrec_total(ventaBD.getPrec_total()+(p.getPrecio()*inputCantidad));
				lstDetalleVentaBD = repodetvent.findByCodVen(ventaBD.getCodVen());
			}
			

			if (lstDetalleVentaBD != null && lstDetalleVentaBD.size() > 0) {
				procesoDesdeBD(ventaBD,lstDetalleVentaBD,inputCantidad,model,p);
				model.addAttribute("lstDetalleVentaBD", repodetvent.findByCodVen(ventaBD.getCodVen()));
				
			}else {
				procesoPrimerRegistro(model, p, inputCantidad, usuario);
			}
			
			return "carrito";
		} else {
			
			return "detalleProducto";

		}

	}
	
	void procesoDesdeBD(Venta objVenta, List<DetalleVenta> lstDetalleVenta, Integer inputCantidad,Model model, Producto p) {
		//Existen registros de venta y detalle venta 
		//Por ello añaden al detalle los valores
		
		DetalleVenta objDetVent = null;
		boolean existe = false;
		for (DetalleVenta objDetalleVenta : lstDetalleVenta) {
			//Si el producto existe en la lista
			if (objDetalleVenta.getProducto().getCod_prod() == p.getCod_prod()) {
				existe = true;
				objDetalleVenta.setCantidad(inputCantidad);
				objDetalleVenta.setTotal(inputCantidad * p.getPrecio());
				objDetVent = objDetalleVenta;
				//Actualizamos
				repodetvent.save(objDetalleVenta);
			}
			
		}
		
		
		if(!existe) {
			//Registramos nuevo item 
			DetalleVenta objDetvent = new DetalleVenta();
			objDetvent.setCodVen(objVenta.getCodVen());
			objDetvent.setProducto(p);
			objDetvent.setCantidad(inputCantidad);
			objDetvent.setTotal((inputCantidad * p.getPrecio()));
			lstDetalleVenta.add(objDetvent);
			repodetvent.save(objDetvent);
		}
		
		calculoPrecioTotal(objVenta, lstDetalleVenta);
		model.addAttribute("objVenta", objVenta);
		model.addAttribute("lstDetalleVenta", lstDetalleVenta);

	}
	
	void calculoPrecioTotal(Venta objVenta, List<DetalleVenta> lstDetalleVenta) {
		double precioTotal = 0.0;
		for (DetalleVenta objDetVent: lstDetalleVenta) {
			precioTotal += objDetVent.getTotal();
		}
		objVenta.setPrec_total(precioTotal);
	}
	

	void procesoPrimerRegistro(Model model, Producto p, Integer inputCantidad, @ModelAttribute Usuario usuario) {
		
		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		
		//Integer ventaId = (int) (repovent.count() + 1);
		Venta objVenta = new Venta();
		
		objVenta.setPrec_total(p.getPrecio()* inputCantidad);
		objVenta.setCodusu(usuario.getCodusu()); // usuario.getCod_usu());
		LocalDate localDate = LocalDate.now();
		objVenta.setFecha_bol(localDate.toString());
		//objVenta.setCodVen(ventaId);
		objVenta.setEstado("P");
		objVenta.setCod_pago("1"); // Visa
		repovent.save(objVenta);
		
		
		objVenta = repovent.findByCodusuAndEstado(usuario.getCodusu(), "P").get(0);
		
		DetalleVenta objDetvent = new DetalleVenta();
		objDetvent.setProducto(p);
		objDetvent.setCantidad(inputCantidad);
		System.out.println("total:" + (inputCantidad * p.getPrecio()));
		objDetvent.setTotal((inputCantidad * p.getPrecio()));
		objDetvent.setCodVen(objVenta.getCodVen());
		
		List<DetalleVenta> lstDetalleVenta = new ArrayList<DetalleVenta>();
		lstDetalleVenta.add(objDetvent);
		//Registramos el detalle
		repodetvent.save(objDetvent);
		
		model.addAttribute("objVenta", objVenta);
		model.addAttribute("lstDetalleVenta", lstDetalleVenta);
	}
	
	@GetMapping("delete/cart/{id}")
	public String deletecart(@PathVariable("id") final Integer idDetvent,
			@ModelAttribute Usuario usuario, Model model, @ModelAttribute Producto p, @ModelAttribute DetalleVenta dv){
		
		dv = repodetvent.findById(idDetvent).get();
		p = repop.findById(dv.getProducto().getCod_prod()).get();
		System.out.println(dv.getCantidad());
		
		p.setStock(p.getStock()+dv.getCantidad());
		repop.save(p);
		repodetvent.deleteById(idDetvent);
		
		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		
		//devolver el stock a la normalidad
		//p = repop.findById(com.pet.util.Constantes.CODIGOPROD).get();
		
		
		Venta objVenta = repovent.findByEstado("P").get(0);
		
		//Venta objVenta = (Venta) model.getAttribute("objVenta");
		if(objVenta != null) {
			
			
			List<DetalleVenta> lstDetalleVenta = (List<DetalleVenta>) repodetvent.findByCodVen(objVenta.getCodVen());
			model.addAttribute("lstDetalleVenta", lstDetalleVenta);
			calculoPrecioTotal(objVenta, lstDetalleVenta);
			model.addAttribute("objVenta", objVenta);
		}
		
		return "carrito";
	}
	
	@GetMapping("/producto/buscar")
	public String abrirbuscar(@ModelAttribute Producto producto, @ModelAttribute Usuario usuario, Model model) {
		
		System.out.println("enviar "+ producto.getCod_prod());
		
		try {
			producto = repop.findById(producto.getCod_prod()).get();
			
			
		} catch (Exception e) {
			
			model.addAttribute("mensaje","Producto No Encontrado");
			usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
			model.addAttribute("usuario", usuario);
			model.addAttribute("lstProductos", repop.findAll());
			return "catalogo";
		}
		
		producto = repop.findById(producto.getCod_prod()).get();

		model.addAttribute("producto", producto);
		
		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		return "detalleProducto";
		
	}
	
	@GetMapping("/listarCarrito")
	public String listarCarrito(@ModelAttribute Usuario usuario, Model model) {
		
		

		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		
		System.out.println(usuario.getCodusu());
		
		
		try {
			//Venta objVenta = repovent.findByEstado("P").get(0);
			List<Venta> lista = repovent.findByCodusuAndEstado(usuario.getCodusu(), "P");
			Venta objVenta = lista.get(0);
			
			//Venta objVenta = (Venta) model.getAttribute("objVenta");
			if(objVenta != null) {
				//List<DetalleVenta> lstDetalleVenta = (List<DetalleVenta>) repodetvent.findByCodVen(objVenta.getCod_Ven());
				List lstDetalleVenta = repodetvent.findByCodVen(objVenta.getCodVen());
				model.addAttribute("lstDetalleVenta", lstDetalleVenta);
				calculoPrecioTotal(objVenta, lstDetalleVenta);
				model.addAttribute("objVenta", objVenta);
				//System.out.println(objVenta.getEstado());
			}
			return "carrito";
		} catch (Exception e) {
			
			model.addAttribute("mensaje", "No tiene ningun producto agregado en su carrito");
			return "principal";
		}
		
		
	}
	
	
	
	
	@PostMapping("/realizarventa")
	public String Vender(@ModelAttribute Usuario usuario,@ModelAttribute Producto p, Model model, @ModelAttribute Venta v, @RequestParam double precio_total) {
		model.addAttribute("venta", repodetvent.findByCodVen(v.getCodVen()));
		System.out.println(precio_total);
		System.out.println("Seteado correctamente");
		
		model.addAttribute("total", precio_total);
		
		Venta objVenta = repovent.findByEstado("P").get(0);
		objVenta.setEstado("R");
		objVenta.setPrec_total(precio_total);
		repovent.save(objVenta);
		
		System.out.println(objVenta.getEstado());
		
		
		usuario = repou.findById(com.pet.util.Constantes.CODIGO).get();
		model.addAttribute("usuario", usuario);
		
		model.addAttribute("lstProductos", repop.findAll());
		return "compra-realizada";
	}
	
}
