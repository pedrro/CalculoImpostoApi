package imposto.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import imposto.api.Client.Usuario;
import imposto.api.Client.UsuarioClient;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ImpostoController {
    @Autowired ImpostoRepository impostoRepository;
    @Autowired UsuarioClient usuarioClient;

    @RequestMapping(value = "/calculaImposto/{id}", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Imposto> calculaImposto(@PathVariable UUID id) {
        Usuario usuario = usuarioClient.pegaUsuarioPorId(id);
        Imposto impostoUsuario = new Imposto(usuario.getId(), usuario.getSalario());
        impostoRepository.save(impostoUsuario);

        return new ResponseEntity<Imposto>(impostoUsuario ,HttpStatus.CREATED );
    }
}


