package com.apssouza.controllers;

import com.apssouza.services.ToDoService;
import com.apssouza.entities.ToDo;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author apsouza
 */
public class ToDoResource {

    long id;
    ToDoService manager;

    public ToDoResource(long id, ToDoService manager) {
        this.id = id;
        this.manager = manager;
    }

    @PutMapping
    public ToDo save(ToDo todo) {
        todo.setId(id);
        return manager.save(todo);
    }

    @GetMapping
    public ToDo find() {
        return manager.findById(id);
    }

    @DeleteMapping
    public void delete() {
        manager.delete(id);
    }

    @PutMapping("/status")
    public ResponseEntity<?>  statusUpdate(JsonNode statusUpdate) {
        JsonNode done = statusUpdate.get("done");
        if (!done.asBoolean()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    header("reason", "JSON should contains field done").
                    build();
        }
        
        ToDo todo = manager.updateStatus(id, done.asBoolean());
        if (todo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    header("reason", "todo with id " + id + " does not exist").
                    build();
        } else {
            return ResponseEntity.ok(todo);
        }
    }

}
