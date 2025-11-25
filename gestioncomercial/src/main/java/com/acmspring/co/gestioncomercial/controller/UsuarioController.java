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

    // Consultas avanzadas
    // 1. Buscar usuarios por apellido
    @GetMapping("/apellido/{apellido}")
    public List<UsuarioEntity> findByApellido(@PathVariable String apellido) {
        return usuarioService.findByApellido(apellido);
    }

    // 2. Listar usuarios de una ciudad específica
    @GetMapping("/ciudad/{ciudadId}")
    public List<UsuarioEntity> findByCiudad(@PathVariable Long ciudadId) {
        return usuarioService.findByCiudadId(ciudadId);
    }

    // 3. Listar usuarios por departamento
    @GetMapping("/departamento/{departamentoId}")
    public List<UsuarioEntity> findByDepartamento(@PathVariable Long departamentoId) {
        return usuarioService.findByDepartamentoId(departamentoId);
    }

    // 4. Buscar usuarios cuyo nombre contenga X texto
    @GetMapping("/buscar")
    public List<UsuarioEntity> findByNombreContaining(@RequestParam String texto) {
        return usuarioService.findByNombreContaining(texto);
    }
}

