package com.acmspring.co.gestioncomercial.services;

import com.acmspring.co.gestioncomercial.entity.DepartamentoEntity;
import com.acmspring.co.gestioncomercial.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<DepartamentoEntity> getAll() {
        return departamentoRepository.findAll();
    }

    public Optional<DepartamentoEntity> getById(Long id) {
        return departamentoRepository.findById(id);
    }

    public DepartamentoEntity create(DepartamentoEntity departamento) {
        return departamentoRepository.save(departamento);
    }

    public DepartamentoEntity update(Long id, DepartamentoEntity departamento) {
        if (departamentoRepository.existsById(id)) {
            departamento.setId(id);
            return departamentoRepository.save(departamento);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (departamentoRepository.existsById(id)) {
            departamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

