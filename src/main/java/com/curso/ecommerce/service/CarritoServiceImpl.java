package com.curso.ecommerce.service;

import com.curso.ecommerce.model.Carrito;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.repository.ICarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private ICarritoRepository carritoRepository;

    @Override
    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @Override
    public Optional<Carrito> findById(Integer id) {
        return carritoRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        carritoRepository.deleteById(id);
    }

    @Override
    public List<Carrito> findByUsuario(Usuario usuario) {
        return carritoRepository.findByUsuario(usuario);
    }
}