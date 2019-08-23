package com.zhifa.rabbitmq.quickstart.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.zhifa.rabbitmq.quickstart.RabbitMqUtil;

public class FanoutConsumer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = RabbitMqUtil.getChannel(connection);
        //声明队列
        String exchangeName="fanout-exchange";
        String exchangeType="fanout";
        String queueName="fanout-que";
        String routeKey="";

        //声明交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,false,null);
        //声明队列
        channel.queueDeclare(queueName,true,false,false,null);
        //绑定关系
        channel.queueBind(queueName, exchangeName, routeKey);
        //创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, queueingConsumer);
        int i=0;
        long startTime = System.currentTimeMillis();
        while (true){
            i++;
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            byte[] body = delivery.getBody();
            String msg = new String(body);
            System.out.println(msg);
        }

    }
}
