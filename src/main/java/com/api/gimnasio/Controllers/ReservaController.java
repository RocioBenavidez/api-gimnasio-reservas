package com.api.gimnasio.Controllers;

import com.api.gimnasio.DTO.ReservaDTO;
import com.api.gimnasio.Models.Clase;
import com.api.gimnasio.Models.Reserva;
import com.api.gimnasio.Models.Usuario;
import com.api.gimnasio.Repositories.ClaseRepository;
import com.api.gimnasio.Repositories.ReservaRepository;
import com.api.gimnasio.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody ReservaDTO reservaDTO){
        if (reservaDTO.getId_usuario() == null || reservaDTO.getId_clase() == null){
            return ResponseEntity.badRequest().build();
        }
        Usuario usuario = usuarioRepository.findById((reservaDTO.getId_usuario())).orElseGet(null);
        if (usuario == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Clase clase = claseRepository.findById(reservaDTO.getId_clase()).orElseGet(null);
        if (clase == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //Verifica si hay cupos disponibles

        if (clase.getCuposDisponibles() <=0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }else {
            clase.setCuposDisponibles(clase.getCuposDisponibles()-1);
        }



        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setFecha(new Date());
        nuevaReserva.setUsuario(usuario);
        nuevaReserva.setClase(clase);

        Reserva reservaSave = reservaRepository.save(nuevaReserva);

        return  ResponseEntity.status(HttpStatus.CREATED).body(reservaSave);

    }

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas(){
        List<Reserva> reservas = reservaRepository.findAll();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Reserva> getReservaById(@PathVariable Long id){
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        return reservaOptional.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO){
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        if (!reservaOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = usuarioRepository.findById(reservaDTO.getId_usuario()).orElse(null);
        Clase clase = claseRepository.findById(reservaDTO.getId_clase()).orElse(null);

        if (usuario == null || clase == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Reserva reservaExistente = reservaOptional.get();
        reservaExistente.setFecha(reservaDTO.getFecha());
        reservaExistente.setUsuario(usuario);
        reservaExistente.setClase(clase);

        Reserva reservaUpdate = reservaRepository.save(reservaExistente);
        return  ResponseEntity.ok(reservaUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id){
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        if (!reservaOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        reservaRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
