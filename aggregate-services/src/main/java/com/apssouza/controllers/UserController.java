package com.apssouza.controllers;

import com.apssouza.clients.UserClient;
import com.apssouza.pojos.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author apssouza
 */
@RequestMapping("/accounts")
@RestController
public class UserController {

    @Autowired
    UserClient userClient;

    @GetMapping
    public List<User> all() {
        return this.userClient.all();
    }

}
