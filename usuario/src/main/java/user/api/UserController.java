package user.api;

import com.mongodb.MongoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    User user;

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> criaUsuario(@RequestBody User newUser) {
        user = new User(newUser.name, newUser.salary);
        userRepository.save(user);
        return new ResponseEntity<User>(user, CREATED);
    }

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> retornaUsuarios() {
        return new ResponseEntity<List<User>>(userRepository.findAll(), OK);
    }

    @RequestMapping(value = "{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> retornaUsuario(@PathVariable("id") UUID id) {
        return new ResponseEntity<User>(userRepository.findOne(id), OK);
    }

    @RequestMapping(value = "{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deletaUsuario(@PathVariable("id") UUID id) {
        try {
            user = userRepository.findOne(id);
            userRepository.delete(id);
        } catch (MongoException mongo) {
            System.out.println(mongo);
            return new ResponseEntity<User>(INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<User>(user, NO_CONTENT);
    }
}
