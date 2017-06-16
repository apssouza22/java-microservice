package com.apssouza.mailservice.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Event sourcing module configuration
 *
 * @author apssouza
 */
@ComponentScan({"com.apssouza.eventsourcing"})
@Configuration
public class EventSourcingConfig {

}
