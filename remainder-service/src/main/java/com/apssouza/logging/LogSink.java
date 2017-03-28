package com.apssouza.logging;

import org.springframework.stereotype.Component;

@Component
@FunctionalInterface
public interface LogSink {

    void log(String msg);
}
