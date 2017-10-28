package com.apssouza.events;

import com.apssouza.entities.ToDo;
import com.apssouza.infra.AppEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Interface To Do changed event
 *
 * @author apssouza
 */
public interface TodoChangedEvent extends AppEvent {

    @JsonIgnore
    int getPriority();

    ToDo getTodo();

}
