package com.acmspring.co.gestioncomercial.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "venta_producto")
public class VentaProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cantidad;

    @ManyToOne (cascade= CascadeType.PERSIST)
    @JoinColumn(name = "producto_id_fk", nullable = false)
    private ProductoEntity producto;

    @ManyToOne (cascade= CascadeType.PERSIST)
    @JoinColumn(name = "venta_id_fk", nullable = false)
    private VentaEntity venta;
}
