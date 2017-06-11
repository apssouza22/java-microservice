package com.apssouza.services;

import com.apssouza.pojos.User;

/**
 * User service.
 *
 * @author apssouza
 */
public interface UserService {

    User getUserByEmail(String email);

}
