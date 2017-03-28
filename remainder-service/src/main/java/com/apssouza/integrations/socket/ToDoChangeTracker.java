package com.apssouza.integrations.socket;


import com.apssouza.annotations.ChangeEvent;
import com.apssouza.entities.ToDo;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@ServerEndpoint(value = "/changes")
@Component
public class ToDoChangeTracker {

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnClose
    public void onClose() {
        this.session = null;
    }
    
    @EventListener
    public void onToDoChange(@ChangeEvent(ChangeEvent.Type.CREATION) ToDo todo) throws EncodeException {
        if (this.session != null && this.session.isOpen()) {
            try {
                ObjectNode event = JsonNodeFactory.instance.objectNode().
                        put("id", todo.getId()).
                        put("cause", "creation");
                this.session.getBasicRemote().sendText(todo.toString());
            } catch (IOException ex) {
                //we ignore this
            }
        }
    }

}
