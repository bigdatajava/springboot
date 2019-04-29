package com.chao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@SpringBootApplication
@Component
@EnableScheduling
public class Producer {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Queue queue;

	@Scheduled(fixedDelay = 5000)
	public void send() {

		String result = "测试消息队列" + System.currentTimeMillis();
		jmsMessagingTemplate.convertAndSend(queue, result);
		System.out.println(result);

	}

	public static void main(String[] args) {

		SpringApplication.run(Producer.class, args);
	}
}
