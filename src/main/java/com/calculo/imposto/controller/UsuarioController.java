package com.calculo.imposto.controller;

import com.calculo.imposto.model.Usuario;
import com.calculo.imposto.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @Autowired UsuarioRepository usuarioRepository;


    @RequestMapping(value = "/usuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> criaUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = new Usuario(usuario.nome, usuario.salario);
        usuarioRepository.save(novoUsuario);
        return new ResponseEntity<Usuario>(novoUsuario, HttpStatus.CREATED);
    }
}
