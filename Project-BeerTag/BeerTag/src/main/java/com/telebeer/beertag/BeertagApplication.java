package com.telebeer.beertag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class BeertagApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeertagApplication.class, args);
    }
}
