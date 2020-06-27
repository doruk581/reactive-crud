package com.doruk.reactive.reactivecrudproject.repository;

import com.doruk.reactive.reactivecrudproject.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
