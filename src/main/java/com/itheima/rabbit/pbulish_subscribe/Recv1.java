package com.itheima.rabbit.pbulish_subscribe;

import com.itheima.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv1 {
   private static final String EXCHANGE_NAME="test_exchange_fanout";
   private static final String QUEUE_NAME="test_exchange_fanout_email";
    public static void main(String[] args) throws IOException, TimeoutException {

    Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override

            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String s = new String(body, "utf-8");
                System.out.println("<Exchange_Recv1>msg"+s);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {

                    System.out.println("Exchange_Recv1处理完成一条消息");
                    channel.basicAck(envelope.getDeliveryTag(),true);
                }
            }
        };
        boolean autoAck=true;//ture就是自动确认，否则手动确认
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);

    }
}
