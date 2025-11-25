package com.acmspring.co.gestioncomercial.repository;

import com.acmspring.co.gestioncomercial.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    
    // 5. Listar productos por categoría
    @Query("SELECT p FROM CategoriaEntity c JOIN c.productos p WHERE c.Id = :categoriaId")
    List<ProductoEntity> findByCategoriaId(@Param("categoriaId") Integer categoriaId);
    
    // 6. Buscar productos por rango de precio
    List<ProductoEntity> findByPrecioBetween(Double precioMin, Double precioMax);
    
    // 7. Ordenar productos por precio asc/desc
    List<ProductoEntity> findAllByOrderByPrecioAsc();
    List<ProductoEntity> findAllByOrderByPrecioDesc();
    
    // 8. Listar productos creados después de una fecha
    List<ProductoEntity> findByFechaCreacionAfter(LocalDateTime fecha);
    
    // 14. Listar los productos más vendidos
    @Query("SELECT vp.producto, SUM(vp.cantidad) as total FROM VentaProductoEntity vp " +
           "GROUP BY vp.producto ORDER BY total DESC")
    List<Object[]> findProductosMasVendidos();
}



