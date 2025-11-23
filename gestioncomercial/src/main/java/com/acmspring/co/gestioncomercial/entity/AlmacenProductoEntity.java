package com.acmspring.co.gestioncomercial.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "almacen_producto")
public class AlmacenProductoEntity {
    @Id
    private Long id;
    private Long stock;

    @ManyToOne (cascade= CascadeType.PERSIST)
    @JoinColumn(name = "producto_id_fk", nullable = false)
    private ProductoEntity producto;

    @ManyToOne (cascade= CascadeType.PERSIST)
    @JoinColumn(name = "almacen_id_fk", nullable = false)
    private AlmacenEntity almacen;
}
