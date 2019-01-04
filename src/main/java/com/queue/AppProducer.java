package com.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppProducer {

    private static final String url = "tcp://127.0.0.1:61616";
    private static final String qName = "qTest";

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
        Destination destination = session.createQueue(qName);
        //6.创建生产者。producer
        MessageProducer producer = session.createProducer(destination);



        for(int i=0;i<100;i++) {
            //7.创建消息
            TextMessage textMessage = session.createTextMessage("test msg" + i);
            //8.发布消息
            producer.send(destination,textMessage);

            System.out.println("msg send successed!" + textMessage.getText());
        }
        //9.关闭连接
        connection.close();
    }
}
