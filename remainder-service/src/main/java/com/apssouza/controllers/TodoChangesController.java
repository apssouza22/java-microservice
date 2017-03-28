package com.apssouza.controllers;

import com.apssouza.events.TodoChangedEvent;
import com.apssouza.monitors.TodoEventChangesMonitor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/todo-changes")
@RestController
public class TodoChangesController {

    @Autowired
    TodoEventChangesMonitor monitor;

    @GetMapping
    public List<TodoChangedEvent> expose() {
        return this.monitor.getRecentEvents();
    }

}
