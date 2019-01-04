package com.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppConsumer {
    private static final String url = "tcp://127.0.0.1:61616";
    private static final String tName = "tTest";

    public static void main(String [] args) throws Exception {
        //1.创建连接工厂ConnectionFactory
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        //2.创建连接 Connection
        Connection connection = factory.createConnection();
        //3.启动连接
        connection.start();
        //4.创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建一个目的地
        Destination destination = session.createTopic(tName);
        //6.创建一个消费者
        MessageConsumer messageConsumer = session.createConsumer(destination);
        //7.创建监听器
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接受消息" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //8.关闭连接
//        connection.close();
    }
}
