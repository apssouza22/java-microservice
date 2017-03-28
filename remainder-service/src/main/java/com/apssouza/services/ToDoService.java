package com.apssouza.services;

import com.apssouza.repositories.TodoRepository;
import com.apssouza.entities.ToDo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ToDoService {

    @Autowired
    private TodoRepository todoRepository;

    public ToDo findById(long id) {
        return this.todoRepository.findOne(id);

    }

    public void delete(long id) {
        this.todoRepository.deleteById(id);
    }

    public List<ToDo> all() {
        return this.todoRepository.findAll();
    }

    public ToDo save(ToDo todo) {
        return this.todoRepository.save(todo);
    }

    public ToDo updateStatus(long id, boolean done) {
        ToDo todo = this.findById(id);
        if (todo == null) {
            return null;
        }
        todo.setDone(done);
        this.todoRepository.save(todo);
        return todo;
    }

}
