package org.jnosql.demo.endgame.spring.mongodb;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.SpringDataMongo3Driver;
import com.github.cloudyrock.spring.v5.MongockSpring5;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class SpringBootDataMongodbApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootDataMongodbApplication.class, args);
    }

    @Bean
    public MongockSpring5.MongockInitializingBeanRunner mongockInitializingBeanRunner(
            ApplicationContext springContext,
            MongoTemplate mongoTemplate){
        return MongockSpring5.builder()
                .setDriver(SpringDataMongo3Driver
                        .withDefaultLock(mongoTemplate))
                .addChangeLogsScanPackage("org.jnosql.demo.endgame.spring.mongodb.changelogs")
                .setSpringContext(springContext)
                .buildInitializingBeanRunner();
    }
}
