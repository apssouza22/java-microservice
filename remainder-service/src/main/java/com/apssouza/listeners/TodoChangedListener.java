package com.apssouza.listeners;

import com.apssouza.events.TodoChangedEvent;
import com.apssouza.monitors.TodoEventChangesMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TodoChangedListener {

    @Autowired
    TodoEventChangesMonitor changesMonitor;

    @EventListener
    public void onTodoChange(TodoChangedEvent event) {
        changesMonitor.addNewEvent(event);        
    }

}
