package com.apssouza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

// curl -X POST -vu android-users:123456 http://localhost:8016/oauth/token -H "Accept: application/json" -d "password=1234&username=apssouza22@gmail.com&grant_type=password&scope=write&client_secret=123456&client_id=android-users"
// curl -v POST http://127.0.0.1:8016/bookmarks -H "Authorization: Bearer 717cb6c2-e41a-40a0-9ae7-d464e636ed59""
// curl http://127.0.0.1:8016/accounts?access_token=717cb6c2-e41a-40a0-9ae7-d464e636ed59
@SpringBootApplication
@EnableFeignClients
public class BasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

}
