package com.apssouza.controllers;

import com.apssouza.clients.TodoClient;
import com.apssouza.pojos.Todo;
import com.apssouza.pojos.User;
import com.apssouza.services.TodoService;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

/**
 * To Do entry point
 *
 * @author apssouza
 */
@RequestMapping("/todos")
@RestController
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping
    public List<Todo> all() {
        return this.todoService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Todo todo, BindingResult result) {
        todoService.createTodo(todo);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();

    }

}
