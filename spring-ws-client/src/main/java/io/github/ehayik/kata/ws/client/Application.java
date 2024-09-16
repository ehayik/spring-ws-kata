package io.github.ehayik.kata.ws.client;

import static java.util.stream.Collectors.joining;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Application {

    @Bean
    CommandLineRunner loadPersons(PersonsClient personsClient) {
        return args -> {
            var personFullNames = personsClient.getPersons(2).getPersons().stream()
                    .map(person -> person.getFirstname() + " " + person.getLastname())
                    .collect(joining(","));
            log.info("Loaded persons: {}", personFullNames);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
