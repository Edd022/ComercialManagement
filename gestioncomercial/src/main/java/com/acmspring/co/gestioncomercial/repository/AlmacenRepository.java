package com.acmspring.co.gestioncomercial.repository;

import com.acmspring.co.gestioncomercial.entity.AlmacenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlmacenRepository extends JpaRepository<AlmacenEntity, Long>{
    List<AlmacenEntity> findAlmacenById(Long id);
}
