package com.zhifa.rabbitmq.quickstart.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhifa.rabbitmq.quickstart.RabbitMqUtil;
import com.zhifa.rabbitmq.quickstart.entity.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DirectProducer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = RabbitMqUtil.getChannel(connection);
        String exchangeName = "direct-exchange";
        String routeKey = "direct-routeKey";
        User zhifa = new User("zhifa", 18);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new ObjectOutputStream(byteArrayOutputStream).writeObject(zhifa);
        byte[] array = byteArrayOutputStream.toByteArray();
        for (int i = 0; i < 10; i++) {
            channel.basicPublish(exchangeName, routeKey, null, array);
        }
        RabbitMqUtil.close(connection, channel);

    }
}
