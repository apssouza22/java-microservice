/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apssouza.eventsourcing.read.listeners;

import com.apssouza.eventsourcing.events.EmailCreatedEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author apssouza
 */
@Component
public class UserDeletedListener {

    private static final Logger LOG = Logger.getLogger(UserDeletedListener.class.getName());
    
    @EventListener
    public void listener(EmailCreatedEvent event){
        LOG.log(Level.ALL, event.getEmail().toString());
    }
}
