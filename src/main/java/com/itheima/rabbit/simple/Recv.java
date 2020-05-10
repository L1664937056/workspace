package com.itheima.rabbit.simple;

import com.itheima.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;


public class Recv {

    static final String QUEUE_NAME="test_simple_queue";
    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(QUEUE_NAME,true,consumer);
        while (true){
           QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            byte[] body = delivery.getBody();

            String s = new String(body);

            System.out.println("<Recv>msg"+s);

        }


    }
}
