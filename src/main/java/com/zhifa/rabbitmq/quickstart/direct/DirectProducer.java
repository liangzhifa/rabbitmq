package com.zhifa.rabbitmq.quickstart.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhifa.rabbitmq.quickstart.RabbitMqUtil;

import java.io.IOException;

public class DirectProducer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = RabbitMqUtil.getChannel(connection);
        String exchangeName="direct-exchange";
        String routeKey="direct-routeKey";
        for (int i = 0; i < 10; i++) {
            try {
                channel.basicPublish(exchangeName, routeKey, null, String.valueOf(i).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RabbitMqUtil.close(connection, channel);

    }
}
