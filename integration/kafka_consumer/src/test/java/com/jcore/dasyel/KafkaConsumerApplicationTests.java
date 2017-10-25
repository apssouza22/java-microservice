package com.jcore.dasyel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KafkaConsumerApplicationTests {

	@Test
    @SuppressWarnings("unchecked")
	public void contextLoads() {
	}
}
