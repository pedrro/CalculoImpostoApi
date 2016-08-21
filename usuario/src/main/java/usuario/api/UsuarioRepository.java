package usuario.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import usuario.api.Usuario;

import java.util.UUID;


public interface UsuarioRepository extends MongoRepository<Usuario, UUID> {
}
