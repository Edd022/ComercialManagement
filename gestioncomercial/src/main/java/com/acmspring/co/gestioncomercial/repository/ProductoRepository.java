package com.acmspring.co.gestioncomercial.repository;

import com.acmspring.co.gestioncomercial.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
}

