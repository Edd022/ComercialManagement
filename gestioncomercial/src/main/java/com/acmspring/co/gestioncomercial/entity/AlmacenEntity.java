package com.acmspring.co.gestioncomercial.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "almacen")
public class AlmacenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "ciudad_id_fk")
    private Ciudad ciudad;

    /*
    @OneToMany (mappedBy = "almacen", cascade = CascadeType.ALL)
    private List<AlmacenProductoEntity> almacenProductos = new java.util.ArrayList<>();*/
}
