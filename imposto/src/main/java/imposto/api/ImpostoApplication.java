package imposto.api;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@EnableMongoRepositories
public class ImpostoApplication  extends AbstractMongoConfiguration{

	public static void main(String[] args) {
		SpringApplication.run(ImpostoApplication.class, args);
	}

    @Override
    protected String getDatabaseName() {
        return "ImpostoDb";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient();
    }
}
