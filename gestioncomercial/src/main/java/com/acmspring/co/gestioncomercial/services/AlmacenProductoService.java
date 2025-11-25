package com.acmspring.co.gestioncomercial.services;

import com.acmspring.co.gestioncomercial.entity.AlmacenProductoEntity;
import com.acmspring.co.gestioncomercial.repository.AlmacenProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlmacenProductoService {

    @Autowired
    AlmacenProductoRepository repoAlmacenProducto;

    public List<AlmacenProductoEntity> getAll(){return repoAlmacenProducto.findAll();}

    public Optional<AlmacenProductoEntity> getById(Long id) {
        return repoAlmacenProducto.findById(id);
    }

    public AlmacenProductoEntity create(AlmacenProductoEntity almacenProducto) {
        return repoAlmacenProducto.save(almacenProducto);
    }

    public AlmacenProductoEntity update(Long id, AlmacenProductoEntity almacenProducto) {
        if (repoAlmacenProducto.existsById(id)) {
            almacenProducto.setId(id);
            return repoAlmacenProducto.save(almacenProducto);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (repoAlmacenProducto.existsById(id)) {
            repoAlmacenProducto.deleteById(id);
            return true;
        }
        return false;
    }
}
