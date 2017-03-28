package com.airhacks.doit.business.reminders.boundary;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author airhacks.com
 */
public class JsonDecoder implements Decoder.TextStream<JsonObject> {

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public JsonObject decode(Reader reader) throws DecodeException, IOException {
        try (JsonReader jsonReader = Json.createReader(reader)) {
            return jsonReader.readObject();
        }
    }

    @Override
    public void destroy() {
    }

}
