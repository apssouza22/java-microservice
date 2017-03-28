/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apssouza.listeners;

import com.apssouza.events.TodoServiceMethodCalledEvent;
import com.apssouza.monitors.TodoServiceMethodMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author apssouza
 */
@Component
public class TodoServiceMethodListener {
    
    @Autowired
    private TodoServiceMethodMonitor methodMonitor;
    
    @EventListener
    public void onMethodCalled(TodoServiceMethodCalledEvent event){
        methodMonitor.addNewEvent(event);
    }
    
}
