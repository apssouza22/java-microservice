package com.apssouza.controllers;

import com.apssouza.services.ToDoService;
import com.apssouza.entities.ToDo;
import com.apssouza.exceptions.DataNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author apssouza
 */
@RequestMapping("/todos")
@RestController
public class ToDoController {

    @Autowired
    ToDoService toDoService;

    @GetMapping
    public List<ToDo> all() {
        return this.toDoService.all();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ToDo todo) {
        ToDo saved = this.toDoService.save(todo);
        Long id = saved.getId();
        if (id != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable long id, ToDo toDo) {
        return toDoService.findById(id)
                .map(todo -> {
                    toDoService.save(todo);
                    return ResponseEntity.ok(todo);
                }).orElseThrow(() -> new DataNotFoundException("Todo not found"));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable long id) {
        Optional<ToDo> findById = toDoService.findById(id);
        return findById.map(todo -> {
            return ResponseEntity.ok(todo);
        }).orElseThrow(() -> new DataNotFoundException("Todo not found"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        toDoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("{id}/status")
    public ResponseEntity<?> statusUpdate(@PathVariable long id, JsonNode statusUpdate) {
        JsonNode status = statusUpdate.get("status");
        if (status == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    header("reason", "JSON should contains field done").
                    build();
        }
        ToDo todo = toDoService.updateStatus(
                id, 
                ToDo.TodoStatus.valueOf(status.asText())
        );
        return ResponseEntity.ok(todo);
    }

}
