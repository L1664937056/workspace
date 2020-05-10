package com.itheima.rabbit.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
//获取MQ连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/vhost");
        connectionFactory.setUsername("user");
        connectionFactory.setPassword("1234");
        Connection connection = connectionFactory.newConnection();

        return connection;

    }


}
