package com.publisher;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Publisher {

    public static void main(String[] args) {

        ConnectionFactory factory=new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");

        try {
            Connection con=factory.createConnection();
            Session session=con.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Destination destination=session.createQueue("demo2");
            TextMessage textMessage=session.createTextMessage("First message two");

            MessageProducer producer=session.createProducer(destination);
            producer.send(textMessage);
            System.out.println("message sent");

            session.close();
            con.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
