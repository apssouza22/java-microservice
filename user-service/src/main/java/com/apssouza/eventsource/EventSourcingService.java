/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apssouza.eventsource;

import com.apssouza.aggregates.Aggregate;
import com.apssouza.events.DomainEvent;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author apssouza
 */
public interface EventSourcingService {
    
    Aggregate save(Aggregate aggregate) ;
    
    List<DomainEvent> getRelatedEvents(UUID uuid);
}
