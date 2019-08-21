package com.zhifa.rabbitmq.quickstart;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = ChannelUtil.getChannel();
        //声明队列
        channel.queueDeclare("que1",true,false,false,null);

    }
}
