package org.jnosql.demo.endgame.spring.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MongoApp.class, args);
    }
}
