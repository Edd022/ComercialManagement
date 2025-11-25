package com.acmspring.co.gestioncomercial.repository;

import com.acmspring.co.gestioncomercial.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepository extends JpaRepository<VentaEntity, Long> {

    // 11. Listar ventas por usuario cliente
    @Query("SELECT v FROM VentaEntity v WHERE v.cliente.id = :clienteId")
    List<VentaEntity> findByClienteId(@Param("clienteId") Integer clienteId);

    // 12. Consultar total vendido por fecha
    @Query("SELECT v.fechaVenta, SUM(vp.cantidad * vp.producto.precio) " +
           "FROM VentaEntity v JOIN VentaProductoEntity vp ON v.id = vp.venta.id " +
           "WHERE DATE(v.fechaVenta) = DATE(:fecha) " +
           "GROUP BY v.fechaVenta")
    List<Object[]> findTotalVendidoPorFecha(@Param("fecha") LocalDateTime fecha);

    // 13. Buscar ventas con monto mayor a X valor
    @Query("SELECT v FROM VentaEntity v JOIN VentaProductoEntity vp ON v.id = vp.venta.id " +
           "GROUP BY v.id HAVING SUM(vp.cantidad * vp.producto.precio) > :monto")
    List<VentaEntity> findVentasConMontoMayorA(@Param("monto") Double monto);
}



