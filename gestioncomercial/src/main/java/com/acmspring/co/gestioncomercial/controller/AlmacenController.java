package com.acmspring.co.gestioncomercial.controller;

import com.acmspring.co.gestioncomercial.entity.AlmacenEntity;
import com.acmspring.co.gestioncomercial.services.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/almacen")
public class AlmacenController {

    @Autowired
    private AlmacenService almacenService;

    @GetMapping
    public List<AlmacenEntity> getAll() {
        return almacenService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlmacenEntity> getById(@PathVariable Long id) {
        Optional<AlmacenEntity> almacen = almacenService.getById(id);
        return almacen.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlmacenEntity> create(@RequestBody AlmacenEntity almacen) {
        AlmacenEntity created = almacenService.create(almacen);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlmacenEntity> update(@PathVariable Long id, @RequestBody AlmacenEntity almacen) {
        AlmacenEntity updated = almacenService.update(id, almacen);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (almacenService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

