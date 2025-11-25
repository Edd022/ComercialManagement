package com.acmspring.co.gestioncomercial.services;

import com.acmspring.co.gestioncomercial.entity.VentaProductoEntity;
import com.acmspring.co.gestioncomercial.repository.VentaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaProductoService {

    @Autowired
    private VentaProductoRepository ventaProductoRepository;

    public List<VentaProductoEntity> getAll() {
        return ventaProductoRepository.findAll();
    }

    public Optional<VentaProductoEntity> getById(Long id) {
        return ventaProductoRepository.findById(id);
    }

    public VentaProductoEntity create(VentaProductoEntity ventaProducto) {
        return ventaProductoRepository.save(ventaProducto);
    }

    public VentaProductoEntity update(Long id, VentaProductoEntity ventaProducto) {
        if (ventaProductoRepository.existsById(id)) {
            ventaProducto.setId(id);
            return ventaProductoRepository.save(ventaProducto);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (ventaProductoRepository.existsById(id)) {
            ventaProductoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

