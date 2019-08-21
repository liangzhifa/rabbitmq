package com.zhifa.rabbitmq.quickstart.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhifa.rabbitmq.quickstart.RabbitMqUtil;

import java.io.IOException;

public class TopicProducer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = RabbitMqUtil.getChannel(connection);
        String exchangeName="topic-exchange";
        String routeKey1="user.save";
        String routeKey2="user.update";
        String routeKey3="user.delete.abc";

        try {
            channel.basicPublish(exchangeName, routeKey1, null, routeKey1.getBytes());
            channel.basicPublish(exchangeName, routeKey2, null, routeKey2.getBytes());
            channel.basicPublish(exchangeName, routeKey3, null, routeKey3.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        RabbitMqUtil.close(connection, channel);

    }
}
