package com.apssouza.services;

import com.apssouza.pojos.Todo;
import com.apssouza.pojos.User;
import java.util.List;

/**
 *
 * @author apssouza
 */
public interface TodoService {
    
    public List<User> getAll();

    public List<Todo> getTodoByUserEmaill(String email);

    Todo createTodo(Todo todo);
}
