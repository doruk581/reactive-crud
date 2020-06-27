package com.doruk.reactive.reactivecrudproject.controller;

import com.doruk.reactive.reactivecrudproject.model.User;
import com.doruk.reactive.reactivecrudproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public Mono<ResponseEntity<User>> create(@RequestBody User user) {
        return userRepository.save(user)
                .map(ResponseEntity::ok);
    }

    @GetMapping
    public Flux listUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/{userId}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable String userId, @RequestBody User user) {
        return userRepository.findById(userId)
                .flatMap(dbUser -> {
                    dbUser.setAge(user.getAge());
                    dbUser.setName(user.getName());
                    dbUser.setSalary(user.getSalary());
                    return userRepository.save(dbUser);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{userId}")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable String userId) {
        return userRepository.findById(userId)
                .flatMap(existingUser -> userRepository.delete(existingUser).then(Mono.just(ResponseEntity.ok().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
