package com.acmspring.co.gestioncomercial.repository;

import com.acmspring.co.gestioncomercial.entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}

