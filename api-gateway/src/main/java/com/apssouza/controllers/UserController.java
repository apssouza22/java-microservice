package com.apssouza.controllers;

import com.apssouza.clients.TodoClient;
import com.apssouza.clients.UserClient;
import com.apssouza.pojos.Todo;
import com.apssouza.pojos.User;
import com.apssouza.services.TodoService;
import com.apssouza.services.UserService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User entry point
 *
 * @author apssouza
 */
@RequestMapping("/accounts")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TodoService todoService;

    @GetMapping
    public List<User> all() {
        return this.userService.getAll();
    }

    @GetMapping("/me")
    public User me(OAuth2Authentication auth) {
        return this.userService.getUserByEmail(auth.getName());
    }

    @GetMapping("/me/todos")
    public List<Todo> todos(OAuth2Authentication auth) {
        return this.todoService.getTodoByUserEmaill(auth.getName());
    }

    @PostMapping("/me/todos")
    public Todo create(@RequestBody Todo todo) {
        return this.todoService.createTodo(todo);
    }

}
