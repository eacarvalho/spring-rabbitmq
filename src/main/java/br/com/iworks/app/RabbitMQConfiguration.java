package br.com.iworks.app;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {ModuleConfiguration.SCAN_PACKAGE})
public class RabbitMQConfiguration {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Value("${exchange.cadastro}")
    private String exchangeCadastro;

    @Value("${exchange.imagem}")
    private String exchangeImagem;

    @Value("${routerkey.cadastro}")
    private String routerKeyCadastro;

    @Value("${routerkey.complemento}")
    private String routerKeyComplemento;

    @Value("${routerkey.imagem}")
    private String routerKeyImagem;

    @Value("${fila.cadastro}")
    private String filaCadastro;

    @Value("${fila.imagem}")
    private String filaImagem;

    @Value("${spring.rabbitmq.listener.concurrency}")
    private Integer concurrentConsumers;

    @Value("${spring.rabbitmq.listener.max-concurrency}")
    private Integer maxConcurrentConsumers;

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(concurrentConsumers);
        factory.setMaxConcurrentConsumers(maxConcurrentConsumers);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        final RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public DirectExchange exchangeCadastro() {
        return new DirectExchange(exchangeCadastro);
    }

    @Bean
    public Queue filaCadastro() {
        return new Queue(filaCadastro);
    }

    @Bean
    public Binding filaCadastroBinding(final Queue filaCadastro, final DirectExchange exchangeCadastro) {
        return BindingBuilder.bind(filaCadastro).to(exchangeCadastro).with(routerKeyCadastro);
    }

    @Bean
    public Binding filaComplementoBinding(final Queue filaCadastro, final DirectExchange exchangeCadastro) {
        return BindingBuilder.bind(filaCadastro).to(exchangeCadastro).with(routerKeyComplemento);
    }

    @Bean
    public DirectExchange exchangeImagem() {
        return new DirectExchange(exchangeImagem);
    }

    @Bean
    public Queue filaImagem() {
        return new Queue(filaImagem);
    }

    @Bean
    public Binding filaImagemBinding(final Queue filaImagem, final DirectExchange exchangeImagem) {
        return BindingBuilder.bind(filaImagem).to(exchangeImagem).with(routerKeyImagem);
    }
}