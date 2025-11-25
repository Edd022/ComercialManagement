package com.acmspring.co.gestioncomercial.controller;

import com.acmspring.co.gestioncomercial.entity.CategoriaEntity;
import com.acmspring.co.gestioncomercial.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("apigc/v1/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaEntity> getAll() {
        return categoriaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEntity> getById(@PathVariable Integer id) {
        Optional<CategoriaEntity> categoria = categoriaService.getById(id);
        return categoria.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaEntity> create(@RequestBody CategoriaEntity categoria) {
        CategoriaEntity created = categoriaService.create(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaEntity> update(@PathVariable Integer id, @RequestBody CategoriaEntity categoria) {
        CategoriaEntity updated = categoriaService.update(id, categoria);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (categoriaService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

