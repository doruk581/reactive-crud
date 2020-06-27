package com.doruk.reactive.reactivecrudproject.configuration;

import com.doruk.reactive.reactivecrudproject.handler.UserHandler;
import com.doruk.reactive.reactivecrudproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public RouterFunction<ServerResponse> route() {
        UserHandler userHandler = new UserHandler(userRepository);
        return RouterFunctions
                .route(POST("/users").and(contentType(APPLICATION_JSON)), userHandler::createUser)
                .andRoute(GET("/users").and(accept(APPLICATION_JSON)), userHandler::listUser)
                .andRoute(GET("/users/{userId}").and(accept(APPLICATION_JSON)), userHandler::getUserById)
                .andRoute(PUT("/users").and(accept(APPLICATION_JSON)), userHandler::createUser)
                .andRoute(DELETE("/users/userId").and(accept(APPLICATION_JSON)), userHandler::deleteUser);
    }
}
