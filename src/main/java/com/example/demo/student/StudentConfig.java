package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){

        return args ->{
            Student michele = new Student((long) 1193109, "Michele", "michele.baldisseri@gmail.com", 24, LocalDate.of(1997, 05, 12));
            Student alex = new Student((long) 1193119, "Alex", "alex.baldisseri@gmail.com", 22, LocalDate.of(1997, 01, 22));

            repository.saveAll(List.of(michele,alex));
        };
    }
}
