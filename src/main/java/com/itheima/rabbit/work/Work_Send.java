package com.itheima.rabbit.work;

import com.itheima.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Work_Send {
    static final String QUEUE_NAME="test_work_queue";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        for (int i = 0; i < 100; i++) {
            String msg="hello work_queue!"+i;
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println("发一条消息："+msg);
Thread.sleep(20*i);
        }

        channel.close();
        conn.close();

    }
}
