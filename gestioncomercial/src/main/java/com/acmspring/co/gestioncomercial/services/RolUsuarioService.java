package com.acmspring.co.gestioncomercial.services;

import com.acmspring.co.gestioncomercial.entity.RolUsuarioEntity;
import com.acmspring.co.gestioncomercial.repository.RolUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolUsuarioService {

    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;

    public List<RolUsuarioEntity> getAll() {
        return rolUsuarioRepository.findAll();
    }

    public Optional<RolUsuarioEntity> getById(Long id) {
        return rolUsuarioRepository.findById(id);
    }

    public RolUsuarioEntity create(RolUsuarioEntity rolUsuario) {
        return rolUsuarioRepository.save(rolUsuario);
    }

    public RolUsuarioEntity update(Long id, RolUsuarioEntity rolUsuario) {
        if (rolUsuarioRepository.existsById(id)) {
            rolUsuario.setId(id);
            return rolUsuarioRepository.save(rolUsuario);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (rolUsuarioRepository.existsById(id)) {
            rolUsuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

