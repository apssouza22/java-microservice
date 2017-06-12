package com.apssouza.integrations.socket;

import com.apssouza.pojos.SocketOutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * A socket endpoint controller
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
