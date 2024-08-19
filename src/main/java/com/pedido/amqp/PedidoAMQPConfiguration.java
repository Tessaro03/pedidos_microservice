package com.pedido.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoAMQPConfiguration {

    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    // Enviar msg para pagamento informando que pedido foi concluido
    @Bean
    public Queue criaFilaPedidoConcluido(){ 
        return QueueBuilder.nonDurable("pedido.concluido").build(); 
    }

    @Bean
    public Queue criaFilaPedidoCanceladoProduto(){ 
        return QueueBuilder.nonDurable("pedido.cancelado-produto").build(); 
    }

    @Bean
    public Queue criaFilaPedidoCanceladoPagamento(){ 
        return QueueBuilder.nonDurable("pedido.cancelado-pagamento").build(); 
    }

    // Receber msg de Fanout Echange de pagamento informando que o mesmo foi concluido
    @Bean
    public Queue criaFilaPagamentoConfirmadoPedido(){ 
        return QueueBuilder.nonDurable("pagamento.confirmado-pedido").build(); 
    }
    @Bean 
    public FanoutExchange fanoutExchange() {     
        return new FanoutExchange("pagamento.ex"); 
    }  
    @Bean
    public Binding bindingPagamentoPedido(){
        return BindingBuilder.bind(criaFilaPagamentoConfirmadoPedido()).to(fanoutExchange());
    }

    @Bean
    public Queue criaFilaPagamentoPedidoEntregue(){ 
        return QueueBuilder.nonDurable("pedido.entregue").build(); 
    }

    @Bean
    public Queue criaFilaProdutoSeparado(){ 
        return QueueBuilder.nonDurable("produto.separado").build(); 
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter); 
        return rabbitTemplate;
    }

    @Bean 
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin) { 
        return event -> rabbitAdmin.initialize(); 
    }
}
