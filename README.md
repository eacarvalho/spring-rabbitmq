# spring-rabbitmq
Arquitetura de mensageria com RabbitMQ 3.6.1 (protcolo AMQP) e spring boot 1.3.3.

# Tecnologias
Maven 3.3.3, java 8, spring boot 1.3.3 (spring-boot-starter-amqp) e lombok 1.16.6.

# Como iniciar a aplicação
mvn spring-boot:run

# Como acessar a aplicação
Para publicar mensagem na fila e consumir basta acessar: http://localhost:8080/filas/1

# Mac: Como instalar
Para instalar o RabbitMQ 3.6.1 é recomendável usar o homebrew e seguir os passos:

1. brew update
2. brew install rabbitmq

# Mac: Como iniciar e acessar o console administrativo do RabbotMQ
Para iniciar, parar ou ver status do serviço do RabbitMQ:

1. /usr/local/sbin/rabbitmq-server
2. /usr/local/sbin/rabbitmqctl stop
3. /usr/local/sbin/rabbitmqctl status

Após instalar, ativar o plugin que dá acesso ao console web de gerenciamento do RabbitMQ:

1. /usr/local/sbin/rabbitmq-plugins enable rabbitmq_management
2. http://localhost:15672 (padrão é guest/guest)

Configurar:

1. Criar virtual host chamado dev01_iworks e usuário e senha dev_iworks que tenha acesso ao virtual host

# Configurações do Spring Boot
No arquivo application.properties colocar as entradas:`

```
spring.rabbitmq.addresses=localhost:5672
spring.rabbitmq.username=dev_iworks
spring.rabbitmq.password=dev_iworks
spring.rabbitmq.virtual-host=dev01_iworks
```

As configurações de exchange, routing key e queue ficam na classe RabbitMQConfiguration.java que faz o binding configurando as filas no RabbitMQ.

Assim, tem-se as seguintes configurações:

1. Exchange: "iworks-exchange-cadastro" -> 2 Routing Keys: "works-routerkey-cadastro", "works-routerkey-complemento" -> 1 fila: iworks-fila-cadastro"
2. Exchange: "iworks-exchange-imagem" -> 1 Routing Key: "iworks-routerkey-imagem", "works-routerkey-complemento" -> 1 fila: "iworks-fila-imagem"

- Para produzir uma mensagem na fila basta:
```
@Autowired
private RabbitTemplate rabbitTemplate;

@Value("${exchange.cadastro}")
private String exchangeCadastro;

@Value("${routerkey.cadastro}")
private String routerKeyCadastro;

public void send() {
    rabbitTemplate.convertAndSend(exchangeCadastro, routerKeyCadastro, dto);
}
```

- Para escutar uma mensagem na fila basta:
```
@RabbitLstener(queues = "${fila.cadastro}")
public void receive(Message mensagem) {}
```
