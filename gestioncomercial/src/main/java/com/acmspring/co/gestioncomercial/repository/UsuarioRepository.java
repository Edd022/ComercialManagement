package com.acmspring.co.gestioncomercial.repository;

import com.acmspring.co.gestioncomercial.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
}

