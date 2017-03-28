package com.apssouza.monitrs;

import com.apssouza.events.TodoChangedEvent;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TodoEventsMonitor {

    //@Autowired
    //LogSink LOG;

    CopyOnWriteArrayList<TodoChangedEvent> recentEvents;

    @PostConstruct
    public void init() {
        this.recentEvents = new CopyOnWriteArrayList<>();
    }

    @EventListener
    public void onCallEvent(TodoChangedEvent event) {
        //LOG.log(event.toString());
        this.recentEvents.add(event);
    }

    public List<TodoChangedEvent> getRecentEvents() {
        return this.recentEvents;
    }

    public LongSummaryStatistics getStatistics() {
        return this.recentEvents.stream().
                collect(Collectors.summarizingLong(TodoChangedEvent::getPriority));
    }

}
