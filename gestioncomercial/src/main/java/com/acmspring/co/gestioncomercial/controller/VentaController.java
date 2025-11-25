package com.acmspring.co.gestioncomercial.controller;

import com.acmspring.co.gestioncomercial.entity.VentaEntity;
import com.acmspring.co.gestioncomercial.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("apigc/v1/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<VentaEntity> getAll() {
        return ventaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaEntity> getById(@PathVariable Long id) {
        Optional<VentaEntity> venta = ventaService.getById(id);
        return venta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VentaEntity> create(@RequestBody VentaEntity venta) {
        VentaEntity created = ventaService.create(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaEntity> update(@PathVariable Long id, @RequestBody VentaEntity venta) {
        VentaEntity updated = ventaService.update(id, venta);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (ventaService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

