package com.acmspring.co.gestioncomercial.services;

import com.acmspring.co.gestioncomercial.entity.ProductoEntity;
import com.acmspring.co.gestioncomercial.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoEntity> getAll() {
        return productoRepository.findAll();
    }

    public Optional<ProductoEntity> getById(Long id) {
        return productoRepository.findById(id);
    }

    public ProductoEntity create(ProductoEntity producto) {
        return productoRepository.save(producto);
    }

    public ProductoEntity update(Long id, ProductoEntity producto) {
        if (productoRepository.existsById(id)) {
            producto.setId(id);
            return productoRepository.save(producto);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

