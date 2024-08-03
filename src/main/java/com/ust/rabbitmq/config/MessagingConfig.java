package com.ust.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

	@Value("${ust.rabbitmq.queue}")
	String queuename;
	
	@Value("${ust.rabbitmq.exchange}")
	String exchange;
	
	@Value("${ust.rabbitmq.routingkey}")
	private String routingkey;
	
	@Bean
	public Queue queue() {
		return new Queue(queuename);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(converter());
		return template;
		
	}
}
