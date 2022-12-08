package com.example.ds2022_30242_stecko_daiana_2_sender;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ds202230242SteckoDaiana2SenderApplication {
	@Bean
	CachingConnectionFactory connectionFactory() {
		String URI = System.getenv("CLOUDAMQP_URL");
		if (URI == null) URI = "amqps://txzqbsrt:0gQsbQLfv6ZDaqs5l5xmSRO372GHgdTq@goose.rmq2.cloudamqp.com/txzqbsrt";
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setUri(URI);
		return cachingConnectionFactory;
	}
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
	public static void main(String[] args) {
		SpringApplication.run(Ds202230242SteckoDaiana2SenderApplication.class, args);
	}

}
