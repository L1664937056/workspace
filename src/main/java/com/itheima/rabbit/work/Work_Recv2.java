package com.itheima.rabbit.work;

import com.itheima.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;


public class Work_Recv2 {
    static int i=0;
    static final String QUEUE_NAME="test_work_queue";
    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false,false, null);
        channel.basicQos(1);//一次分发一个

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String s = new String(body, "utf-8");
                System.out.println("<Work_Recv2>msg"+s);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("Work_Recv2处理完成一条消息"+"共处理了"+ ++i);
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }

            }
        };
        boolean autoAck=false;
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);


    }


    }

