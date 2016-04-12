package br.com.iworks.core.listener.service.impl;

import br.com.iworks.core.listener.service.CadastroListenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
public class CadastroListenerServiceImpl implements CadastroListenerService {

    @Value("${fila.cadastro}")
    private String filaCadastro;

    @Value("${routerkey.cadastro}")
    private String routerKeyCadastro;

    @Value("${routerkey.complemento}")
    private String routerKeyComplemento;

    @Override
    @RabbitListener(queues = "${fila.cadastro}")
    public void receive(Message mensagem) throws UnsupportedEncodingException {
        if (mensagem.getMessageProperties().getReceivedRoutingKey().equals(routerKeyCadastro)) {
            log.info("Mensagem: {} recebida. Fila: {}. Router Key: {}",
                    new String(mensagem.getBody(), "UTF-8"),
                    filaCadastro, routerKeyCadastro);
        } else {
            log.info("Mensagem: {} recebida. Fila: {}. Router Key: {}",
                    new String(mensagem.getBody(), "UTF-8"),
                    filaCadastro, routerKeyComplemento);
        }
    }
}
