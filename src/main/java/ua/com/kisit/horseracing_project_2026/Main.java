package ua.com.kisit.horseracing_project_2026;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
