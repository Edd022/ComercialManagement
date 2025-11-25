package com.acmspring.co.gestioncomercial.controller;

import com.acmspring.co.gestioncomercial.entity.Ciudad;
import com.acmspring.co.gestioncomercial.services.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ciudad")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @GetMapping
    public List<Ciudad> getAll() {
        return ciudadService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> getById(@PathVariable Long id) {
        Optional<Ciudad> ciudad = ciudadService.getById(id);
        return ciudad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ciudad> create(@RequestBody Ciudad ciudad) {
        Ciudad created = ciudadService.create(ciudad);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ciudad> update(@PathVariable Long id, @RequestBody Ciudad ciudad) {
        Ciudad updated = ciudadService.update(id, ciudad);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (ciudadService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

