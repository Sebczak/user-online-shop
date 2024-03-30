package com.company.configurations;

import com.company.entities.User;
import com.company.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User sebastian = new User(
                    "Sebasitan",
                    "Lachut",
                    "seb@gmail.com",
                    LocalDate.of(2004,10,12)
            );

            User kamil = new User(
                    "Kamil",
                    "Lachut",
                    "kamil@gmail.com",
                    LocalDate.of(2005,10,12)
            );
            repository.saveAll(List.of(sebastian, kamil));
        };
    }
}
