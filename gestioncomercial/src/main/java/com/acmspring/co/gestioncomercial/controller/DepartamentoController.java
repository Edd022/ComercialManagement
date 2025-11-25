package com.acmspring.co.gestioncomercial.controller;

import com.acmspring.co.gestioncomercial.entity.DepartamentoEntity;
import com.acmspring.co.gestioncomercial.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("apigc/v1/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public List<DepartamentoEntity> getAll() {
        return departamentoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoEntity> getById(@PathVariable Long id) {
        Optional<DepartamentoEntity> departamento = departamentoService.getById(id);
        return departamento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DepartamentoEntity> create(@RequestBody DepartamentoEntity departamento) {
        DepartamentoEntity created = departamentoService.create(departamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoEntity> update(@PathVariable Long id, @RequestBody DepartamentoEntity departamento) {
        DepartamentoEntity updated = departamentoService.update(id, departamento);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (departamentoService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

