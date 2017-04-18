package com.apssouza.services;

import com.apssouza.pojos.Todo;
import com.apssouza.pojos.User;
import java.util.List;

/**
 *
 * @author apssouza
 */
public interface UserService {
    
    List<User> getAll();

    User getUserByEmail(String email);
    
    Todo createUser(User user);
}
