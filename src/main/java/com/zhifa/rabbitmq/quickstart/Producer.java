package com.zhifa.rabbitmq.quickstart;

import com.rabbitmq.client.Channel;

import java.io.IOException;

public class Producer {
    public static void main(String[] args) throws IOException {
        Channel channel = ChannelUtil.getChannel();
        for (int i = 0; i < 5; i++) {
            channel.basicPublish("ex1", "rk1", null, String.valueOf(i).getBytes());
        }
        ChannelUtil.close();
    }
}
