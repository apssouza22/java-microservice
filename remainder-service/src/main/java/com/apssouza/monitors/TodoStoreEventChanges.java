package com.apssouza.monitors;

import com.apssouza.events.TodoChangedEvent;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * Store ToDo event changes.
 *
 * @author apssouza
 */
@Component
public class TodoStoreEventChanges {

    private CopyOnWriteArrayList<TodoChangedEvent> events;

    @PostConstruct
    public void init() {
        this.events = new CopyOnWriteArrayList<>();
    }

    public void addNewEvent(TodoChangedEvent event) {
        this.events.add(event);
    }

    public List<TodoChangedEvent> getRecentEvents() {
        return this.events;
    }

    public LongSummaryStatistics getStatistics() {
        return this.events.stream().
                collect(Collectors.summarizingLong(TodoChangedEvent::getPriority));
    }
}
