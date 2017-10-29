/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apssouza.bootstrap;

import com.apssouza.repositories.TodoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import com.apssouza.entities.ToDo;
import org.springframework.stereotype.Component;

/**
 *
 * @author apssouza
 */
@Component
public class TodoLoader implements ApplicationListener<ContextRefreshedEvent>  {

    private final TodoRepository todoRepository;
    private final Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    @Autowired
    public TodoLoader(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        ToDo todo1 = new ToDo("apssouza22@gmail.com","Test caption", "description 1", 2);
        ToDo todo2 = new ToDo("gael@gmail.com","Test caption 2", "description 2", 3);
        ToDo todo3 = new ToDo("marcia@gmail.com","Test caption 3", "description 3", 2);
        
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);
        
        log.info("Created the to-dos.");
    }
    
}
