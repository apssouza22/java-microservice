package com.apssouza.monitors;

import com.apssouza.events.TodoServiceMethodInvokedEvent;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * Store TodoService method invoked.
 *
 * @author apssouza
 */
@Component
public class TodoServiceMethodInvokedStore {

    private CopyOnWriteArrayList<TodoServiceMethodInvokedEvent> events;

    @PostConstruct
    public void init() {
        this.events = new CopyOnWriteArrayList<>();
    }

    public void addNewEvent(TodoServiceMethodInvokedEvent event) {
        this.events.add(event);
    }

    public List<TodoServiceMethodInvokedEvent> getRecentChanges() {
        return this.events;
    }

    public LongSummaryStatistics getStatistics() {
        return this.events.stream().
                collect(Collectors.summarizingLong(TodoServiceMethodInvokedEvent::getDuration));
    }
}
