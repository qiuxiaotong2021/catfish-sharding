package com.catfish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.catfish", exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@EnableTransactionManagement
@EntityScan("com.catfish.**.entity")
public class Sharding4Application {

    public static void main(String[] args) {
        SpringApplication.run(Sharding4Application.class, args);
    }
}