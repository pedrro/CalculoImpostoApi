package imposto.api;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;


public interface UsuarioRepository extends MongoRepository<Usuario, UUID> {
}
