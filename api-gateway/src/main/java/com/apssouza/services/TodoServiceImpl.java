package com.apssouza.services;

import com.apssouza.clients.TodoClient;
import com.apssouza.pojos.Todo;
import com.apssouza.pojos.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ToDo service 
 * @author apssouza
 */
@Component
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoClient todoClient;

    @Override
    public List<User> getAll() {
        return todoClient.getAll();
    }

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackTodos")
    public List<Todo> getTodoByUserEmaill(String email) {
        return todoClient.getTodoByUserEmaill(email);
    }

    @Override
    public Todo createTodo(Todo todo) {
        return todoClient.createTodo(todo);
    }

    public List<Todo> getFallbackTodos(String email) {
        return Collections.emptyList();
    }
}
