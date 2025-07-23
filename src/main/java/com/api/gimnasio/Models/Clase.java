package com.api.gimnasio.Models;
import lombok.Data;
import jakarta.persistence.*;
@Data
@Entity
@Table(name = "clases")
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nombre;
    private String hora;
    @Column(name = "cupos_disponibles")
    private int cuposDisponibles;


}
