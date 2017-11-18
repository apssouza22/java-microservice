/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apssouza.eventsourcing.aggregates;

import com.apssouza.infra.AppEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author apssouza
 */
public abstract class AbstractAggregate implements Aggregate {

    protected String uuid;
    
    protected List<AppEvent> changes = new CopyOnWriteArrayList();

    protected CopyOnWriteArrayList<AppEvent> appendChange(AppEvent event) {
        CopyOnWriteArrayList<AppEvent> listChanges = new CopyOnWriteArrayList(changes);
        listChanges.add(event);
        return listChanges;
    }

    @Override
    public List<AppEvent> getUncommittedChanges() {
        return changes;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

}
