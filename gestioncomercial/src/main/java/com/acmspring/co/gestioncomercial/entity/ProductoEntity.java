package com.acmspring.co.gestioncomercial.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "producto")

public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Double precio;
    private String descripcion;
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
}
