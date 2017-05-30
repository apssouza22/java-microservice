/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apssouza.read.listeners;

import com.apssouza.events.UserCreatedEvent;
import com.apssouza.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author apssouza
 */
@Component
public class UserDeletedListener {
    
    @Autowired
    AccountService accountService;
    
    @EventListener
    public void listener(UserCreatedEvent event){
        accountService.delete(event.getAccount());
    }
}
