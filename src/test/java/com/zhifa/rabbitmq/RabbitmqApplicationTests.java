package com.zhifa.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() throws Exception{
        //boolean b = rabbitAdmin.deleteExchange("spring-direct-exchange");
        rabbitAdmin.declareExchange(new DirectExchange("spring-direct-exchange",false,false));

        rabbitAdmin.declareQueue(new Queue("spring-direct-queue",false));

        rabbitAdmin.declareBinding(new Binding("spring-direct-queue", Binding.DestinationType.QUEUE,"spring-direct-exchange","direct",new HashMap<>()));

    }
    @Test
    public void rabbitTemplate() throws Exception{
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().put("desc","信息描述");
        messageProperties.getHeaders().put("type","信息类型");
        //创建消息
        Message message = new Message("Hello".getBytes(),messageProperties);
        for (int i = 0; i < 10000; i++) {
            rabbitTemplate.convertAndSend("spring-direct-exchange","direct","你好啊"+i);

        }
        rabbitTemplate.convertAndSend("spring-direct-exchange","direct",message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().getHeaders().put("attr", "额外的信息");
                return message;
            }
        });
    }

}
