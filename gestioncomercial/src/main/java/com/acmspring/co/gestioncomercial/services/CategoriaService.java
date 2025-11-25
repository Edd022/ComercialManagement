package com.acmspring.co.gestioncomercial.services;

import com.acmspring.co.gestioncomercial.entity.CategoriaEntity;
import com.acmspring.co.gestioncomercial.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaEntity> getAll() {
        return categoriaRepository.findAll();
    }

    public Optional<CategoriaEntity> getById(Integer id) {
        return categoriaRepository.findById(id);
    }

    public CategoriaEntity create(CategoriaEntity categoria) {
        return categoriaRepository.save(categoria);
    }

    public CategoriaEntity update(Integer id, CategoriaEntity categoria) {
        if (categoriaRepository.existsById(id)) {
            categoria.setId(id);
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    public boolean delete(Integer id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

