package com.api.gimnasio.Models;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "usuarios")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nombre;
    private String correo;
    private String contraseña;

}
