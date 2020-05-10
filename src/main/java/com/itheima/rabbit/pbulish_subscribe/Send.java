package com.itheima.rabbit.pbulish_subscribe;

import com.itheima.rabbit.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    static final String EXCHANGE_NAME="test_exchange_fanout";
    public static void main(String[] args) throws IOException, TimeoutException {

        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel();
       channel.exchangeDeclare(EXCHANGE_NAME,"fanout");//分发
        String msg="hello exchange_fanout";
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());

        System.out.println("Send："+msg);

        channel.close();
        conn.close();
    }

    }
