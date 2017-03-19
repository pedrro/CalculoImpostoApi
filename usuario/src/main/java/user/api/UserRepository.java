package user.api;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;


public interface UserRepository extends MongoRepository<User, UUID> {
}
