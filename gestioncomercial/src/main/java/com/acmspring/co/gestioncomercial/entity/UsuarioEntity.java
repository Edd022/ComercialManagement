package com.acmspring.co.gestioncomercial.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 32, nullable = false)
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    @Column(length =32, nullable = false,unique = true)
    private String email;
    @Column(name = "fecha_registro", nullable = false)
    @CreationTimestamp
    private LocalDateTime fechaRegistro;
    @Column(length = 10, nullable = false, unique = true)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "ciudad_id_fk")
    private Ciudad ciudad;

    //el fetch EAGER hace que traiga los datos de la otra tabla
    // (lo trae por defecto ManyToOne), LAZY
    @ManyToOne(cascade = CascadeType.PERSIST)
    //Va en la entidad que maneja la relación con la otra entidad
    //y va a referenciar la clave foránea de la otra entidad
    @JoinColumn(name = "rol_usuario_id_fk", nullable = false)
    private RolUsuarioEntity rolUsuario;
}
