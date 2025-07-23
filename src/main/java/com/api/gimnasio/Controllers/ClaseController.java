package com.api.gimnasio.Controllers;

import com.api.gimnasio.Models.Clase;
import com.api.gimnasio.Repositories.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    @Autowired
    private ClaseRepository claseRepository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Clase>> getAllClases(){
        List<Clase> clases = claseRepository.findAll();
        return ResponseEntity.ok(clases);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Clase> getClaseById(@PathVariable Long id){
        Optional<Clase> clase = claseRepository.findById(id);
        return clase.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClase(@PathVariable Long id){
        if(!claseRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        claseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Clase> updateClase(@PathVariable Long id,@RequestBody Clase updateClase){
        if (!claseRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        updateClase.setId(id);
        Clase savedClase = claseRepository.save(updateClase);
        return ResponseEntity.ok(savedClase);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Clase> createClase(@RequestBody Clase clase){
        Clase savedClase = claseRepository.save(clase);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClase);
    }
    @CrossOrigin
    @GetMapping("/disponibilidad/{id}")
    public ResponseEntity<String> verificarCuposDisponibles(@PathVariable Long id) {
        Optional<Clase> claseOptional = claseRepository.findById(id);

        if (!claseOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La clase no existe.");
        }

        Clase clase = claseOptional.get();

        if (clase.getCuposDisponibles() > 0) {
            return ResponseEntity.ok("Cupos disponibles: " + clase.getCuposDisponibles());
        } else {
            return ResponseEntity.ok("Lo sentimos, no hay cupos disponibles para esta clase.");
        }
    }

}
