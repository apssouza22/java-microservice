package com.apssouza.mailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.apssouza.infra", 
    "com.apssouza.eventsourcing", 
    "com.apssouza.mailservice"
})
public class MailserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailserviceApplication.class, args);
    }
}
