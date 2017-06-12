package com.apssouza.integrations.socket;

import com.apssouza.annotations.ChangeEvent;
import com.apssouza.entities.ToDo;
import com.apssouza.events.TodoChangedEvent;
import com.apssouza.pojos.SocketOutputMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.websocket.EncodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Component responsible to notify socket clients.
 *
 * @author apssouza
 */
@Component
public class TodoChangeSocketNotify {

    @Autowired
    private SimpMessagingTemplate template;

    public void notify(TodoChangedEvent e) throws EncodeException {
        ToDo todo = e.getTodo();
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        Class<? extends TodoChangedEvent> aClass = e.getClass();

        ChangeEvent annotation = aClass.getAnnotation(ChangeEvent.class);
        ChangeEvent.Type value = annotation.value();
        SocketOutputMessage socketOutputMessage = new SocketOutputMessage(todo, value.toString(), time);
        template.convertAndSend("/topic/todos", socketOutputMessage);
    }

}
