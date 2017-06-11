
package com.apssouza.kafkaevent.configurations;

import com.apssouza.eventsourcing.events.AbstractDomainEvent;
import com.apssouza.kafkaevent.encoders.EventDeserializer;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

/**
 *
 * @author apssouza
 */
@EnableKafka
@Configuration
public class KafkaEmailConsumerConfig {
    
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    
    @Value(value = "${mail.topic.name}")
    private String topicName;

    public ConsumerFactory<String, AbstractDomainEvent> emailConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, topicName);
        return new DefaultKafkaConsumerFactory<>(props, 
                new org.apache.kafka.common.serialization.StringDeserializer(), 
                new EventDeserializer<>(AbstractDomainEvent.class)
        );
    }
    
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AbstractDomainEvent> emailKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AbstractDomainEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(emailConsumerFactory());
        return factory;
    }
}
