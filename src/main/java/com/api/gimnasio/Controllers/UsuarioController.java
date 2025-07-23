package com.api.gimnasio.Controllers;

import com.api.gimnasio.Models.Usuario;
import com.api.gimnasio.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id){
        return  usuarioRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario updatedUsuario){
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(updatedUsuario.getNombre());
            usuario.setCorreo(updatedUsuario.getCorreo());
            usuario.setContraseña(updatedUsuario.getContraseña());

            Usuario savedUsuario =usuarioRepository.save(usuario);
            return ResponseEntity.ok(savedUsuario);
        }).orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        if (!usuarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
