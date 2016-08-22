package usuario.api;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class UsuarioApiApplication extends AbstractMongoConfiguration {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(UsuarioApiApplication.class, args);
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(environment.getProperty("DOCKER_HOST"));
    }

    @Override
    protected String getDatabaseName() {
        return "UsuarioDb";
    }
}
