package nl.hu.bracketboys.webshop.backend.config;

import nl.hu.bracketboys.webshop.backend.order.controllers.EventConsumer;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConsumerConfiguration {
    @Bean
    public Exchange eventExchange() {
        return new TopicExchange("payments");
    }

    @Bean
    public Queue queue() {
        return new Queue("orderQueue");
    }

    @Bean
    public Binding binding(Queue queue, Exchange eventExchange) {
        return BindingBuilder
                .bind(queue)
                .to(eventExchange)
                .with("order.*")
                .noargs();
    }

    @Bean
    public EventConsumer eventReceiver() {
        return new EventConsumer();
    }
}
