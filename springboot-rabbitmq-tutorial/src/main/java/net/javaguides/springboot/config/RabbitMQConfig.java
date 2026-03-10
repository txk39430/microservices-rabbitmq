package net.javaguides.springboot.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.binding.key}")
    private String routingKey;

    //Spring Bean for RabbitMQ queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    //Spring Bean for RabbitMQ exchange
    @Bean
    public TopicExchange exchange(){
        return  new TopicExchange(exchange);
    }

    // Binding btw queue and exchange using binding key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    //springboot automatically configures these infrastructure beans .....we just need to inject and use these beans
    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
}
