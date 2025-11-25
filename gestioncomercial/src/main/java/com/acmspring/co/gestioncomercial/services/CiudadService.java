package com.acmspring.co.gestioncomercial.services;

import com.acmspring.co.gestioncomercial.entity.Ciudad;
import com.acmspring.co.gestioncomercial.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    public List<Ciudad> getAll() {
        return ciudadRepository.findAll();
    }

    public Optional<Ciudad> getById(Long id) {
        return ciudadRepository.findById(id);
    }

    public Ciudad create(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    public Ciudad update(Long id, Ciudad ciudad) {
        if (ciudadRepository.existsById(id)) {
            ciudad.setId(id);
            return ciudadRepository.save(ciudad);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (ciudadRepository.existsById(id)) {
            ciudadRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

