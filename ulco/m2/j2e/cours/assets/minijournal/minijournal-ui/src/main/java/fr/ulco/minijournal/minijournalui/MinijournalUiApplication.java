package fr.ulco.minijournal.minijournalui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.resilience.annotation.EnableResilientMethods;

@EnableResilientMethods
@SpringBootApplication
public class MinijournalUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinijournalUiApplication.class, args);
    }

}
