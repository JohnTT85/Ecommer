package com.curso.ecommerce.controller;

import com.curso.ecommerce.model.Carrito;
import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.CarritoService;
import com.curso.ecommerce.service.ProductoService;
import com.curso.ecommerce.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("")
    public String verCarrito(Model model, HttpSession session) {
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        List<Carrito> carritos = carritoService.findByUsuario(usuario);
        
        double total = carritos.stream()
            .mapToDouble(carrito -> carrito.getProducto().getPrecio() * carrito.getCantidad())
            .sum();
            
        model.addAttribute("carritos", carritos);
        model.addAttribute("total", total);
        return "carrito/carrito";
    }

    @PostMapping("/agregar")
    public String agregarCarrito(@RequestParam Integer productoId, 
                                @RequestParam Integer cantidad,
                                HttpSession session) {
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        Producto producto = productoService.get(productoId).get();
        
        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setProducto(producto);
        carrito.setCantidad(cantidad);
        
        carritoService.save(carrito);
        
        return "redirect:/carrito";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCarrito(@PathVariable Integer id) {
        carritoService.delete(id);
        return "redirect:/carrito";
    }
}