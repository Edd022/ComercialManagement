package com.acmspring.co.gestioncomercial.controller;

import com.acmspring.co.gestioncomercial.entity.VentaEntity;
import com.acmspring.co.gestioncomercial.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/venta")
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

    // Consultas avanzadas
    // 11. Listar ventas por usuario cliente
    @GetMapping("/cliente/{clienteId}")
    public List<VentaEntity> findByCliente(@PathVariable Integer clienteId) {
        return ventaService.findByClienteId(clienteId);
    }

    // 12. Consultar total vendido por fecha
    @GetMapping("/total-por-fecha")
    public List<Object[]> findTotalVendidoPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {
        return ventaService.findTotalVendidoPorFecha(fecha);
    }

    // 13. Buscar ventas con monto mayor a X valor
    @GetMapping("/monto-mayor")
    public List<VentaEntity> findVentasConMontoMayorA(@RequestParam Double monto) {
        return ventaService.findVentasConMontoMayorA(monto);
    }
}

