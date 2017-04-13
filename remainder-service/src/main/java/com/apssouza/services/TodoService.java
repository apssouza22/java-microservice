package com.apssouza.services;

import com.apssouza.entities.ToDo;
import com.apssouza.exceptions.DataNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author apssouza
 */
public interface TodoService {

    Optional<ToDo> findById(long id);

    Boolean delete(long id) throws DataNotFoundException;

    List<ToDo> all();

    ToDo save(ToDo todo);

    ToDo updateStatus(long id, ToDo.TodoStatus status) throws DataNotFoundException;
    
    ToDo update(Long id, ToDo toDo) throws DataNotFoundException;

    List<ToDo> getByUserEmail(String email);
}
