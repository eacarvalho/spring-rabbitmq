package br.com.iworks.core.listener.service.impl;

import br.com.iworks.core.listener.service.ImagemListenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
public class ImagemListenerServiceImpl implements ImagemListenerService {

    @Value("${fila.imagem}")
    private String filaImagem;

    @Value("${routerkey.imagem}")
    private String routerKeyImagem;

    @Override
    @RabbitListener(queues = "${fila.imagem}")
    public void receive(Message mensagem) throws UnsupportedEncodingException {
        log.info("Mensagem: {} recebida. Fila: {}. Router Key: {}", new String(mensagem.getBody(), "UTF-8"),
                filaImagem, routerKeyImagem);

        log.debug("Mensagem: {} recebida. Fila: {}. Router Key: {}", mensagem, filaImagem, routerKeyImagem);
    }
}
