package com.zhifa.rabbitmq.quickstart.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhifa.rabbitmq.quickstart.RabbitMqUtil;

import java.io.IOException;

public class FanoutProducer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = RabbitMqUtil.getChannel(connection);
        //声明队列
        String exchangeName="fanout-exchange";
        String exchangeType="fanout";
        String queueName="fanout-que";
        String routeKey="";

        try {
            for (int i = 0; i < 10000; i++) {
                channel.basicPublish(exchangeName,routeKey,null,String.valueOf(i).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        RabbitMqUtil.close(connection, channel);

    }
}
