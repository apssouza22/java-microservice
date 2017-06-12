package com.apssouza.listeners;

import com.apssouza.events.TodoServiceMethodInvokedEvent;
import com.apssouza.monitors.TodoServiceMethodInvokedStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * TodoService method invoked listener
 * 
 * @author apssouza
 */
@Component
public class TodoServiceMethodListener {
    
    @Autowired
    private TodoServiceMethodInvokedStore methodMonitor;
    
    @EventListener
    public void onMethodCalled(TodoServiceMethodInvokedEvent event){
        methodMonitor.addNewEvent(event);
    }
    
}
