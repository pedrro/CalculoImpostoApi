package user.api;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Document(collection = "user")
@Value
@Builder
@AllArgsConstructor
public class User {
    @Id
    private UUID id;
    public String name;
    public double salary;

    public User(String name, double salary) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.salary = salary;
    }
}
