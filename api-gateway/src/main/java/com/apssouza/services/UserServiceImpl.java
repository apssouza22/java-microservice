package com.apssouza.services;

import com.apssouza.clients.UserClient;
import com.apssouza.pojos.Todo;
import com.apssouza.pojos.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User Service
 *
 * @author apssouza
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserClient userClient;

    @Override
    public List<User> getAll() {
        return userClient.getAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userClient.getUserByEmail(email);
    }

    @Override
    public Todo createUser(User user) {
        return userClient.createUser(user);
    }

}
