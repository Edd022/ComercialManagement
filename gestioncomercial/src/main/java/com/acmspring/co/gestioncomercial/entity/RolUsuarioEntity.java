package com.acmspring.co.gestioncomercial.entity;

import com.acmspring.co.gestioncomercial.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rol_usuario")
public class RolUsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    /*
    //Se mapea con el nombre que ponemos en java
    //Por lo general trae el tipo de fetch en Lazy
    @OneToMany (mappedBy = "rolUsuario")
    private List<UsuarioEntity> usuarios;
     */
}
