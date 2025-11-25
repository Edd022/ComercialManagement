package com.acmspring.co.gestioncomercial.controller;

import com.acmspring.co.gestioncomercial.entity.UsuarioEntity;
import com.acmspring.co.gestioncomercial.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioEntity> getAll() {
        return usuarioService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> getById(@PathVariable Integer id) {
        Optional<UsuarioEntity> usuario = usuarioService.getById(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> create(@RequestBody UsuarioEntity usuario) {
        UsuarioEntity created = usuarioService.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> update(@PathVariable Integer id, @RequestBody UsuarioEntity usuario) {
        UsuarioEntity updated = usuarioService.update(id, usuario);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (usuarioService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

