package com.acmspring.co.gestioncomercial.repository;

import com.acmspring.co.gestioncomercial.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    // 1. Buscar usuarios por apellido
    List<UsuarioEntity> findByApellido(String apellido);

    // 2. Listar usuarios de una ciudad específica
    @Query("SELECT u FROM UsuarioEntity u WHERE u.ciudad.id = :ciudadId")
    List<UsuarioEntity> findByCiudadId(@Param("ciudadId") Long ciudadId);

    // 3. Listar usuarios por departamento
    @Query("SELECT u FROM UsuarioEntity u WHERE u.ciudad.departamento.id = :departamentoId")
    List<UsuarioEntity> findByDepartamentoId(@Param("departamentoId") Long departamentoId);

    // 4. Buscar usuarios cuyo nombre contenga X texto
    List<UsuarioEntity> findByNombreContainingIgnoreCase(String texto);
}



