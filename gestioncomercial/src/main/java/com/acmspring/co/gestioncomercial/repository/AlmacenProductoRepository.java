package com.acmspring.co.gestioncomercial.repository;

import com.acmspring.co.gestioncomercial.entity.AlmacenProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlmacenProductoRepository extends JpaRepository<AlmacenProductoEntity, Long> {

    // 9. Listar todos los productos de un almacén
    @Query("SELECT ap FROM AlmacenProductoEntity ap WHERE ap.almacen.id = :almacenId")
    List<AlmacenProductoEntity> findByAlmacenId(@Param("almacenId") Long almacenId);
}




