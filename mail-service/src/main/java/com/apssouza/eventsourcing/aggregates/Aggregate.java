package com.apssouza.eventsourcing.aggregates;

import com.apssouza.infra.AppEvent;
import java.util.List;

/**
 * The aggregate interface
 *
 * @author apssouza
 */
public interface Aggregate {

    String getUuid();

    List<AppEvent> getUncommittedChanges();

    Aggregate markChangesAsCommitted();


}
