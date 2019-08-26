package com.zhifa.rabbitmq.quickstart.confirm;

import com.rabbitmq.client.*;
import com.zhifa.rabbitmq.quickstart.RabbitMqUtil;

import java.io.IOException;

public class Produceer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = RabbitMqUtil.getChannel(connection);
        //开启消息确认模式
        channel.confirmSelect();

        String exchangeName = "confirm-exchange";
        String routeKey = "confirm-routeKey";

        //发消息
        for (int i = 0; i < 10; i++) {
            channel.basicPublish(exchangeName, routeKey, null, ("MSG.confirm->" + i).getBytes());
        }

        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("=====ack=======deliveryTag:" + deliveryTag + "===multiple:" + multiple);

            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("=======no ack=======deliveryTag:" + deliveryTag + "===multiple:" + multiple);
            }
        });
    }
}
