package com.acmspring.co.gestioncomercial.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "categoria")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String nombre;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "producto_categoria"
            , joinColumns = @JoinColumn(name = "categoria_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "producto_id_fk")
    )
    private List<ProductoEntity> productos = new java.util.ArrayList<>();
}
