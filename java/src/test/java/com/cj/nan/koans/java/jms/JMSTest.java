package com.cj.nan.koans.java.jms;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.benchmark.Producer;
import org.junit.Test;

import javax.jms.*;
import javax.management.j2ee.statistics.JMSConsumerStats;

public class JMSTest extends TestCase {

    JMSProducer producer;

    @Override
    public void setUp() {
        producer = new JMSProducer();
        thread(producer, false);
    }

    @Override
    public void tearDown() {
        try {
            producer.quit();
        } catch (JMSException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testRecieveTextMessagesViaJMS() throws JMSException {
        //given a text message that was sent via jms to some activemq queue
        producer.sendTextMessage("Hello World!");

        //when consuming that message from the jms queue
        JMSConsumer consumer = new JMSConsumer();
        String theTextMessage = consumer.getMessageFromQueue("vm://localhost","TEST.FOO");

        //then we get the correct message back
        Assert.assertEquals("Hello World!", theTextMessage);
    }

    public static void thread(Runnable runnable, boolean daemon) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }

}
