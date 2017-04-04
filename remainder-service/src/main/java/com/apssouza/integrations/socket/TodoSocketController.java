package com.apssouza.integrations.socket;

import com.apssouza.pojo.SocketOutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 *
 * @author apssouza
 */
@Controller
public class TodoSocketController {

    @MessageMapping("/socket-todos")
    @SendTo("/topic/todos")
    public SocketOutputMessage send(SocketOutputMessage message) throws Exception {
        return message; 
    }
}
