package imposto.api;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

import imposto.api.Client.Usuario;


public interface UsuarioRepository extends MongoRepository<Usuario, UUID> {
}
