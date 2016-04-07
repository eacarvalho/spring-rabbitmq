package br.com.iworks.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("### - Lendo propriedades do Application: " + System.getProperties().toString());
        new SpringApplicationBuilder(Application.class, ModuleConfiguration.class,
                RabbitMQConfiguration.class, "classpath*:applicationContext.xml").run(args);
    }
}