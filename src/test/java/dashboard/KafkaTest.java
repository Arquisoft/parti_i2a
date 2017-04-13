package dashboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import dashboard.listeners.MessageListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import participationSystem.hello.producers.KafkaProducer;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Daniel Ortea on 01-Apr-17.
 * Testing class dedicated to test Kafka Consumer and Producer API
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {

    private static final Logger logger = Logger.getLogger(String.valueOf(KafkaTest.class));
    protected static final String TOPIC = "test";

    @Autowired
    private MessageListener messageListener;

    @Autowired
    private KafkaProducer producer;

    @Test
    public void testSerialization() throws JsonProcessingException, InterruptedException {

    }

    @Test
    public void testSpringConsumerAndProducer() throws InterruptedException, IOException {
        producer.send("test","foo");
        Thread.sleep(5000);
        if(messageListener.getTest())
            logger.info("Message received");
        else
            logger.info("Message not received");
        messageListener.setTest(false);
    }

}
