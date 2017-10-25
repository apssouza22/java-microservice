package com.jcore.dasyel.kafka_producer;

public class ChatMessage {
    private final String contents;
    private final long time;

    public ChatMessage(String contents, long time) {
        this.contents = contents;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public String getContents() {
        return contents;
    }
}
