package com.api.gimnasio.Models;
import lombok.Data;
import jakarta.persistence.*;

import java.util.Date;

@Data
@Entity
@Table(name = "reservas")

public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    @PrePersist
    public void setFechaReserva() {
        // Establece la fecha de la PC en el momento de la persistencia
        this.fecha = new Date();
    }




}
