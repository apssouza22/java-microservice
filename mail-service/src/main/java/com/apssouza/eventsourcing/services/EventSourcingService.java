/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apssouza.eventsourcing.services;

import com.apssouza.eventsourcing.aggregates.Aggregate;
import com.apssouza.eventsourcing.eventstore.EventStream;
import com.apssouza.infra.AppEvent;
import java.util.List;

/**
 *
 * @author apssouza
 */
public interface EventSourcingService {
    
    Aggregate save(Aggregate aggregate) ;
    
    List<AppEvent> getRelatedEvents(String uuid);
    
    EventStream getAggregate(String uuid);
}
