package com.shaazabedin;

import com.shaazabedin.config.ApplicationConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Slf4j
@ImportAutoConfiguration({ApplicationConfig.class})
@SpringBootApplication
@EntityScan("com.shaazabedin")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
