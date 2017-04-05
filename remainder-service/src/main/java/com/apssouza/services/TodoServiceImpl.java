package com.apssouza.services;

import com.apssouza.repositories.TodoRepository;
import com.apssouza.entities.ToDo;
import com.apssouza.exceptions.DataNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Optional<ToDo> findById(long id) {
        return Optional.ofNullable(this.todoRepository.findOne(id));
    }

    @Override
    public Boolean delete(long id) throws DataNotFoundException {
        this.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Not found ToDo id " + id));

        return this.todoRepository.deleteById(id);
    }

    @Override
    public List<ToDo> all() {
        return this.todoRepository.findAll();
    }

    @Override
    public ToDo save(ToDo todo) {
        return this.todoRepository.save(todo);
    }

    @Override
    public ToDo updateStatus(long id, ToDo.TodoStatus status) throws DataNotFoundException {
        return this.findById(id)
                .map((t) -> {
                    t.setStatus(status);
                    return todoRepository.save(t);
                }).orElseThrow(() -> new DataNotFoundException("Not found ToDo id " + id));
    }

}
