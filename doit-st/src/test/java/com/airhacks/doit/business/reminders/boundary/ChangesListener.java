package com.airhacks.doit.business.reminders.boundary;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.json.JsonObject;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

/**
 *
 * @author airhacks.com
 */
public class ChangesListener extends Endpoint {

    JsonObject message;
    CountDownLatch latch = new CountDownLatch(1);

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        session.addMessageHandler(JsonObject.class, (msg) -> {
            message = msg;
            latch.countDown();
            System.out.println("msg = " + msg);
        });
    }

    public JsonObject getMessage() throws InterruptedException {
        latch.await(1, TimeUnit.MINUTES);
        return message;
    }

}
