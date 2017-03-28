
package com.apssouza.monitors;

import com.apssouza.events.TodoServiceMethodCalledEvent;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 *
 * @author apssouza
 */
@Component
public class TodoServiceMethodMonitor {

    private CopyOnWriteArrayList<TodoServiceMethodCalledEvent> events;

    @PostConstruct
    public void init() {
        this.events = new CopyOnWriteArrayList<>();
    }

    public void addNewEvent(TodoServiceMethodCalledEvent event) {
        this.events.add(event);
    }

    public List<TodoServiceMethodCalledEvent> getRecentChanges() {
        return this.events;
    }

    public LongSummaryStatistics getStatistics() {
        return this.events.stream().
                collect(Collectors.summarizingLong(TodoServiceMethodCalledEvent::getDuration));
    }
}
