package com.acmspring.co.gestioncomercial.controller;

import com.acmspring.co.gestioncomercial.entity.AlmacenProductoEntity;
import com.acmspring.co.gestioncomercial.services.AlmacenProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/almacen-producto")
public class AlmacenProductoController {
    @Autowired
    private AlmacenProductoService Service;

    @GetMapping
    public List<AlmacenProductoEntity> getAll(){return Service.getAll();}

    @GetMapping("/{id}")
    public ResponseEntity<AlmacenProductoEntity> getById(@PathVariable Long id) {
        Optional<AlmacenProductoEntity> almacenProducto = Service.getById(id);
        return almacenProducto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlmacenProductoEntity> create(@RequestBody AlmacenProductoEntity almacenProducto) {
        AlmacenProductoEntity created = Service.create(almacenProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlmacenProductoEntity> update(@PathVariable Long id, @RequestBody AlmacenProductoEntity almacenProducto) {
        AlmacenProductoEntity updated = Service.update(id, almacenProducto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (Service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
