package com.curso.ecommerce.service;

import com.curso.ecommerce.model.Carrito;
import com.curso.ecommerce.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface CarritoService {
    Carrito save(Carrito carrito);
    Optional<Carrito> findById(Integer id);
    void delete(Integer id);
    List<Carrito> findByUsuario(Usuario usuario);
}