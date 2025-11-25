package com.acmspring.co.gestioncomercial.services;

import com.acmspring.co.gestioncomercial.entity.VentaEntity;
import com.acmspring.co.gestioncomercial.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<VentaEntity> getAll() {
        return ventaRepository.findAll();
    }

    public Optional<VentaEntity> getById(Long id) {
        return ventaRepository.findById(id);
    }

    public VentaEntity create(VentaEntity venta) {
        return ventaRepository.save(venta);
    }

    public VentaEntity update(Long id, VentaEntity venta) {
        if (ventaRepository.existsById(id)) {
            venta.setId(id);
            return ventaRepository.save(venta);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Consultas avanzadas
    public List<VentaEntity> findByClienteId(Integer clienteId) {
        return ventaRepository.findByClienteId(clienteId);
    }

    public List<Object[]> findTotalVendidoPorFecha(LocalDateTime fecha) {
        return ventaRepository.findTotalVendidoPorFecha(fecha);
    }

    public List<VentaEntity> findVentasConMontoMayorA(Double monto) {
        return ventaRepository.findVentasConMontoMayorA(monto);
    }
}


