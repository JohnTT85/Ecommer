package com.curso.ecommerce.repository;

import com.curso.ecommerce.model.Carrito;
import com.curso.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Integer> {
    List<Carrito> findByUsuario(Usuario usuario);
}