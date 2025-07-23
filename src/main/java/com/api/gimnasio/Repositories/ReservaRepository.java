package com.api.gimnasio.Repositories;

import com.api.gimnasio.Models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {

}
