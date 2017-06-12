package com.apssouza.listeners;

import com.apssouza.events.TodoChangedEvent;
import com.apssouza.integrations.socket.TodoChangeSocketNotify;
import com.apssouza.monitors.TodoStoreEventChanges;
import javax.websocket.EncodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * ToDo Changed listener.
 *
 * @author apssouza
 */
@Component
public class TodoChangedListener {

    @Autowired
    TodoStoreEventChanges changesMonitor;

    @Autowired
    TodoChangeSocketNotify socketNotify;

    @EventListener
    public void onTodoChange(TodoChangedEvent event) throws EncodeException {
        changesMonitor.addNewEvent(event);
        socketNotify.notify(event);
    }

}
