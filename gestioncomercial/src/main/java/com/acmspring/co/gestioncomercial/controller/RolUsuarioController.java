package com.acmspring.co.gestioncomercial.controller;

import com.acmspring.co.gestioncomercial.entity.RolUsuarioEntity;
import com.acmspring.co.gestioncomercial.services.RolUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("apigc/v1/rolusuario")
public class RolUsuarioController {

    @Autowired
    private RolUsuarioService rolUsuarioService;

    @GetMapping
    public List<RolUsuarioEntity> getAll() {
        return rolUsuarioService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolUsuarioEntity> getById(@PathVariable Long id) {
        Optional<RolUsuarioEntity> rolUsuario = rolUsuarioService.getById(id);
        return rolUsuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RolUsuarioEntity> create(@RequestBody RolUsuarioEntity rolUsuario) {
        RolUsuarioEntity created = rolUsuarioService.create(rolUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolUsuarioEntity> update(@PathVariable Long id, @RequestBody RolUsuarioEntity rolUsuario) {
        RolUsuarioEntity updated = rolUsuarioService.update(id, rolUsuario);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (rolUsuarioService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

