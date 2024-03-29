package com.zhifa.rabbitmq.quickstart.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.zhifa.rabbitmq.quickstart.RabbitMqUtil;
import com.zhifa.rabbitmq.quickstart.entity.User;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class DirectConsumer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = RabbitMqUtil.getChannel(connection);
        //声明队列
        String exchangeName="direct-exchange";
        String exchangeType="direct";
        String queueName="direct-que";
        String routeKey="direct-routeKey";

        //声明交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,false,null);
        //声明队列
        channel.queueDeclare(queueName,true,false,false,null);
        //绑定关系
        channel.queueBind(queueName, exchangeName, routeKey);
        //创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, queueingConsumer);
        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            byte[] body = delivery.getBody();
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(body));
            User zhifa = (User) inputStream.readObject();
            System.out.println(zhifa);
        }

    }
}
