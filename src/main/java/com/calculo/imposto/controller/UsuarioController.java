package com.calculo.imposto.controller;

import com.calculo.imposto.model.Usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import lombok.val;

@RestController
public class UsuarioController {

    @RequestMapping(value = "/usuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> criaUsuario(@RequestBody Usuario usuario) {
        val novoUsuario = new Usuario();
        novoUsuario.setId(UUID.randomUUID());
        novoUsuario.setNome(usuario.nome);
        novoUsuario.setSalario(usuario.salario);
        novoUsuario.setInss(usuario.salario);
        novoUsuario.setSegVida(usuario.salario);
        novoUsuario.setVr(usuario.salario);
        novoUsuario.setVt(usuario.salario);
        novoUsuario.setCustoTotal();

        return new ResponseEntity<Usuario>(novoUsuario, HttpStatus.ACCEPTED);
    }
}
