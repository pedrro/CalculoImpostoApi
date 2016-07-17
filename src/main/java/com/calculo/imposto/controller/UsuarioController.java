package com.calculo.imposto.controller;

import com.calculo.imposto.model.Usuario;
import com.calculo.imposto.repository.UsuarioRepository;
import com.mongodb.MongoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class UsuarioController {
    @Autowired UsuarioRepository usuarioRepository;


    @RequestMapping(value = "/usuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> criaUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = new Usuario(usuario.nome, usuario.salario);
        usuarioRepository.save(novoUsuario);
        return new ResponseEntity<Usuario>(novoUsuario, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> retornaUsuarios() {
        return new ResponseEntity<List<Usuario>>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value= "/usuario/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> deletaUsuario(@PathVariable("id") UUID id) {
        Usuario usuario;
        try {
            usuario = usuarioRepository.findOne(id);
            usuarioRepository.delete(id);
        } catch (MongoException mongo) {
            System.out.println(mongo);
            return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Usuario>(usuario,HttpStatus.ACCEPTED);
    }
}
