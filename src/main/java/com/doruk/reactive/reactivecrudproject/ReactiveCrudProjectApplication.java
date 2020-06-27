package com.doruk.reactive.reactivecrudproject;

import com.doruk.reactive.reactivecrudproject.model.User;
import com.doruk.reactive.reactivecrudproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveCrudProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveCrudProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository userRepository) {
        return args -> {
            userRepository.deleteAll()
                    .thenMany(Flux.just(
                            new User("Dhiraj", 23, (double) 3456),
                            new User("Mike", 34, (double) 3421),
                            new User("Hary", 21, (double) 8974)
                    )
                            .flatMap(userRepository::save))
                    .thenMany(userRepository.findAll())
                    .subscribe(System.out::println);
        };
    }

}
