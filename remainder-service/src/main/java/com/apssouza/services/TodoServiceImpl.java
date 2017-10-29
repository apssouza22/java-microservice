package com.apssouza.services;

import com.apssouza.repositories.TodoRepository;
import com.apssouza.entities.ToDo;
import com.apssouza.events.TodoCreatedEvent;
import com.apssouza.exceptions.DataNotFoundException;
import com.apssouza.infra.EventPublisher;
import com.apssouza.monitoring.CallMonitoringAspect;
import com.apssouza.monitoring.Monitored;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
    
    @Autowired
    private EventPublisher publisher;

    @Override
    @Monitored
    public Optional<ToDo> findById(long id) {
        return Optional.ofNullable(this.todoRepository.findOne(id));
    }

    @Override
    @Monitored
    public Boolean delete(long id) throws DataNotFoundException {
        this.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Not found ToDo id " + id));

        return this.todoRepository.deleteById(id);
    }

    @Override
    @Monitored
    public List<ToDo> all() {
        return this.todoRepository.findAll();
    }

    @Override
    @Monitored
    public ToDo save(ToDo todo) {
        ToDo savedTodo = this.todoRepository.save(todo);
        this.publisher.stream(new TodoCreatedEvent(savedTodo));
        return savedTodo;
        
    }

    @Override
    @Monitored
    public ToDo updateStatus(long id, ToDo.TodoStatus status) throws DataNotFoundException {
        return this.findById(id)
                .map((t) -> {
                    t.setStatus(status);
                    return todoRepository.save(t);
                }).orElseThrow(() -> new DataNotFoundException("Not found ToDo id " + id));
    }

    @Override
    @Monitored
    public ToDo update(Long id, ToDo toDo) throws DataNotFoundException {
        return this.findById(id)
                .map(todo -> {
                    todo.setCaption(toDo.getCaption());
                    todo.setDescription(toDo.getDescription());
                    todo.setPriority(toDo.getPriority());
                    return save(todo);
                }).orElseThrow(() -> new DataNotFoundException("User not found"));
    }

    @Override
    @Monitored
    public List<ToDo> getByUserEmail(String email) {
        return this.todoRepository.findByUserEmail(email);
    }

}
