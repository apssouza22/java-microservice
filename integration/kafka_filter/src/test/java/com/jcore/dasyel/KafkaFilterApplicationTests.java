package com.jcore.dasyel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcore.dasyel.kafka_filter.ChatMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaFilterApplicationTests {
	@Autowired
	private Processor processor;

	@Autowired
	private MessageCollector messageCollector;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@SuppressWarnings("unchecked")
	public void outputsOriginalMessageWithAllCapsContent() throws Exception {
		final String jsonInString = objectMapper.writeValueAsString(new ChatMessage("Hello, World", 1));
		final String jsonOutString = objectMapper.writeValueAsString(new ChatMessage("HELLO, WORLD!", 1));
		final Message<String> message = new GenericMessage<>(jsonInString);
		processor.input().send(message);
		final Message<String> received = (Message<String>) messageCollector.forChannel(processor.output()).poll();
		assertThat(received.getPayload(), equalTo(jsonOutString));
	}
}
