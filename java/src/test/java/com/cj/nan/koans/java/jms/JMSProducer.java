package com.cj.nan.koans.java.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSProducer implements Runnable {

    Connection connection;
    Session session;
    Boolean running = false;

    public void sendTextMessage(String theMessage) throws JMSException {
        System.out.println("this is the hashcode of the sendTextMessage Object: " + this.hashCode());
        
        makeSureMessageProviderIsRunning();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("TEST.FOO");

        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        TextMessage message = session.createTextMessage(theMessage);

        producer.send(message);
        System.out.println("Message Sent!");
    }

    private void makeSureMessageProviderIsRunning() {
        while(!this.running) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public void quit() throws JMSException {
        session.close();
        connection.close();
        this.running = false;
    }

    public void run() {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

            connection = connectionFactory.createConnection();
            connection.start();
            this.running = true;
            System.out.println("this is the hashcode of the object thread: " + this.hashCode());
            while(running) {}

//            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//            Destination destination = session.createQueue("TEST.FOO");
//
//            MessageProducer producer = session.createProducer(destination);
//            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//
//            producer.send(theMessage);

            //session.close();
            //connection.close();
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }


}
