package com.calculo.imposto.repository;

import com.calculo.imposto.model.Usuario;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;


public interface UsuarioRepository extends MongoRepository<Usuario, UUID> {
}
