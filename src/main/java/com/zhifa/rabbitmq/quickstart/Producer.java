package com.zhifa.rabbitmq.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Producer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = RabbitMqUtil.getChannel(connection);
        for (int i = 0; i < 10; i++) {
            try {
                channel.basicPublish("", "que1", null, String.valueOf(i).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RabbitMqUtil.close(connection, channel);

    }
}
