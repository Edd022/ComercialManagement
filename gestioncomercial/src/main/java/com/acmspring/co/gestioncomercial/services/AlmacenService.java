package com.acmspring.co.gestioncomercial.services;

import com.acmspring.co.gestioncomercial.entity.AlmacenEntity;
import com.acmspring.co.gestioncomercial.repository.AlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlmacenService {

    @Autowired
    private AlmacenRepository almacenRepository;

    public List<AlmacenEntity> getAll() {
        return almacenRepository.findAll();
    }

    public Optional<AlmacenEntity> getById(Long id) {
        return almacenRepository.findById(id);
    }

    public AlmacenEntity create(AlmacenEntity almacen) {
        return almacenRepository.save(almacen);
    }

    public AlmacenEntity update(Long id, AlmacenEntity almacen) {
        if (almacenRepository.existsById(id)) {
            almacen.setId(id);
            return almacenRepository.save(almacen);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (almacenRepository.existsById(id)) {
            almacenRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Consultas avanzadas
    // 10. Listar almacenes de una ciudad
    public List<AlmacenEntity> findByCiudadId(Long ciudadId) {
        return almacenRepository.findByCiudadId(ciudadId);
    }
}


