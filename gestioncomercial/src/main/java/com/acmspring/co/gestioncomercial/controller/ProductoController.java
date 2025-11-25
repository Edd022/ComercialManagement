package com.acmspring.co.gestioncomercial.controller;

import com.acmspring.co.gestioncomercial.entity.ProductoEntity;
import com.acmspring.co.gestioncomercial.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("apigc/v1/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<ProductoEntity> getAll() {
        return productoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoEntity> getById(@PathVariable Long id) {
        Optional<ProductoEntity> producto = productoService.getById(id);
        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoEntity> create(@RequestBody ProductoEntity producto) {
        ProductoEntity created = productoService.create(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoEntity> update(@PathVariable Long id, @RequestBody ProductoEntity producto) {
        ProductoEntity updated = productoService.update(id, producto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (productoService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

