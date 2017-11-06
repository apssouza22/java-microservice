/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apssouza.eventsourcing.events;

import com.apssouza.eventsourcing.entities.Email;
import com.apssouza.infra.AppEvent;

/**
 *
 * @author apssouza
 */
public interface EmailEvent extends AppEvent{
    
    Email getEmail();
    
}
