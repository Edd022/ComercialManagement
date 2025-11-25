package com.acmspring.co.gestioncomercial.controller;

import com.acmspring.co.gestioncomercial.entity.VentaProductoEntity;
import com.acmspring.co.gestioncomercial.services.VentaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("apigc/v1/ventaproducto")
public class VentaProductoController {

    @Autowired
    private VentaProductoService ventaProductoService;

    @GetMapping
    public List<VentaProductoEntity> getAll() {
        return ventaProductoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaProductoEntity> getById(@PathVariable Long id) {
        Optional<VentaProductoEntity> ventaProducto = ventaProductoService.getById(id);
        return ventaProducto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VentaProductoEntity> create(@RequestBody VentaProductoEntity ventaProducto) {
        VentaProductoEntity created = ventaProductoService.create(ventaProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaProductoEntity> update(@PathVariable Long id, @RequestBody VentaProductoEntity ventaProducto) {
        VentaProductoEntity updated = ventaProductoService.update(id, ventaProducto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (ventaProductoService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

