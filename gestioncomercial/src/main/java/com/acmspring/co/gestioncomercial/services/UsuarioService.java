package com.acmspring.co.gestioncomercial.services;

import com.acmspring.co.gestioncomercial.entity.UsuarioEntity;
import com.acmspring.co.gestioncomercial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioEntity> getAll() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> getById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioEntity create(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    public UsuarioEntity update(Integer id, UsuarioEntity usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public boolean delete(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

