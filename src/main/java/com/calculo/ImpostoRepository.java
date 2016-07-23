package com.calculo;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ImpostoRepository extends MongoRepository<Imposto,UUID> {
}
