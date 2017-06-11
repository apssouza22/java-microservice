package com.apssouza.services;

import com.apssouza.clients.UserClient;
import com.apssouza.pojos.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author apssouza
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserClient userClient;

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackUserByEmail")
    public User getUserByEmail(String email) {
        return userClient.getUserByEmail(email);
    }

    public User getFallbackUserByEmail(String email) {
        return new User();
    }

}
