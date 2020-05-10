package com.itheima.rabbit.simple;

import com.itheima.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    static final String QUEUE_NAME="test_simple_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String msg="hello simple!";
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        System.out.println("发一条消息："+msg);
//添加注释
        channel.close();
        connection.close();

//       第二次提交

    }
}
