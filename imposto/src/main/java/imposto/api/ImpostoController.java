package imposto.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ImpostoController {
    @Autowired UsuarioRepository usuarioRepository;
    @Autowired ImpostoRepository impostoRepository;

    @RequestMapping(value = "/calculaImposto/{id}", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Imposto> calculaImposto(@PathVariable UUID id) {
        Usuario usuario = usuarioRepository.findOne(id);
        Imposto impostoUsuario = new Imposto(usuario.getId(), usuario.getSalario());
        impostoRepository.save(impostoUsuario);

        return new ResponseEntity<Imposto>(impostoUsuario ,HttpStatus.CREATED );
    }
}


