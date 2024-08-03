package com.ust.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ust.rabbitmq.model.OrderStatus;


@Component
public class PurchaseOrderConsumer {
	
	@Value("${ust.rabbitmq.queue}")
	String queuename;
	
	@Value("${ust.rabbitmq.exchange}")
	String exchange;

	@Value("${ust.rabbitmq.routingkey}")
	private String routingkey;
	
	@RabbitListener(queues="ust_queue")
	public void consumeMessageFromQueue(OrderStatus orderStatus) {
		System.out.println("Message received from Queue :"+ orderStatus);
	}


}
