package com.apssouza.infra;

import java.time.Instant;

/**
 *
 * The application event
 *
 * @author apssouza
 */
public interface AppEvent {

    String uuid();

    Instant when();

}
