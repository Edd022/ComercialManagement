package com.acmspring.co.gestioncomercial.repository;

import com.acmspring.co.gestioncomercial.entity.AlmacenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlmacenRepository extends JpaRepository<AlmacenEntity, Long>{
    List<AlmacenEntity> findAlmacenById(Long id);

    // 10. Listar almacenes de una ciudad
    @Query("SELECT a FROM AlmacenEntity a WHERE a.ciudad.id = :ciudadId")
    List<AlmacenEntity> findByCiudadId(@Param("ciudadId") Long ciudadId);
}




