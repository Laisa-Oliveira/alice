package com.example.alice.controller;


import com.example.alice.model.Usuario;
import com.example.alice.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {



    private UsuarioRepository usuarioRepository;
    public UsuarioController(UsuarioRepository usuarioRepository){
        super();
        this.usuarioRepository = usuarioRepository;
    }


    @Autowired
    private UsuarioRepository repository;
    private Object List;


    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = repository.save(usuario);
        //Status 200 - OK - Sucesso
        return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> get() {
        List<Usuario> ususrios = repository.findAll();
        // status 200 - Ok - Sucesso
        return new ResponseEntity<>(ususrios, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Usuario>> getById(@PathVariable Integer id) {
        Optional<Usuario> usuario;
        try {
            usuario = usuarioRepository.findById(id);
            return new ResponseEntity<Optional<Usuario>>(usuario, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Usuario>>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Usuario>> deleteById(@PathVariable Integer id) {
        try {
            usuarioRepository.deleteById(id);
            return new ResponseEntity<Optional<Usuario>>(HttpStatus.OK);

        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Usuario>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id,@RequestBody Usuario newUsuario){
        return usuarioRepository.findById(id)
                .map(usuario ->{
                    usuario.setNome(newUsuario.getNome());
                    usuario.setEmail(newUsuario.getEmail());
                    usuario.setSenha(newUsuario.getSenha());
                    Usuario usuarioUpdated = usuarioRepository.save(usuario);
                    return ResponseEntity.ok().body(usuarioUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
}