package com.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.w3c.dom.Text;

import javax.jms.*;

public class Consumer {
    public static void main(String[] args) {

        ConnectionFactory factory=new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");

        try {
            Connection con=factory.createConnection();
            con.start();

            Session session=con.createSession(false,Session.CLIENT_ACKNOWLEDGE);
            Destination destination=session.createQueue("demo2");

            MessageConsumer consumer=session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage msg=(TextMessage) message;
                    try {
                        System.out.println(msg.getText());
                        msg.acknowledge();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
                    System.out.println("message Consumed");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
