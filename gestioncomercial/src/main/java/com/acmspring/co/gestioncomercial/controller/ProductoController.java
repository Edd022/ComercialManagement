package com.acmspring.co.gestioncomercial.controller;

import com.acmspring.co.gestioncomercial.entity.ProductoEntity;
import com.acmspring.co.gestioncomercial.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/producto")
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

    // Consultas avanzadas
    // 5. Listar productos por categoría
    @GetMapping("/categoria/{categoriaId}")
    public List<ProductoEntity> findByCategoria(@PathVariable Integer categoriaId) {
        return productoService.findByCategoriaId(categoriaId);
    }

    // 6. Buscar productos por rango de precio
    @GetMapping("/precio")
    public List<ProductoEntity> findByRangoPrecio(
            @RequestParam Double precioMin,
            @RequestParam Double precioMax) {
        return productoService.findByRangoPrecio(precioMin, precioMax);
    }

    // 7. Ordenar productos por precio asc/desc
    @GetMapping("/ordenar-precio")
    public List<ProductoEntity> findOrderByPrecio(@RequestParam String orden) {
        if ("desc".equalsIgnoreCase(orden)) {
            return productoService.findAllOrderByPrecioDesc();
        }
        return productoService.findAllOrderByPrecioAsc();
    }

    // 8. Listar productos creados después de una fecha
    @GetMapping("/fecha-desde")
    public List<ProductoEntity> findByFechaCreacionAfter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {
        return productoService.findByFechaCreacionAfter(fecha);
    }

    // 14. Listar los productos más vendidos
    @GetMapping("/mas-vendidos")
    public List<Object[]> findProductosMasVendidos() {
        return productoService.findProductosMasVendidos();
    }
}

