package br.com.iworks.core.service.impl;

import br.com.iworks.core.dto.CadastroDTO;
import br.com.iworks.core.dto.ComplementoDTO;
import br.com.iworks.core.dto.ImagemDTO;
import br.com.iworks.core.service.ProcessamentoProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProcessamentoProducerServiceImpl implements ProcessamentoProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${exchange.cadastro}")
    private String exchangeCadastro;

    @Value("${routerkey.cadastro}")
    private String routerKeyCadastro;

    @Value("${routerkey.complemento}")
    private String routerKeyComplemento;

    @Value("${fila.cadastro}")
    private String filaCadastro;

    @Value("${exchange.imagem}")
    private String exchangeImagem;

    @Value("${routerkey.imagem}")
    private String routerKeyImagem;

    @Value("${fila.imagem}")
    private String filaImagem;

    @Override
    public void send() {
        CadastroDTO cadastro = new CadastroDTO(155, "Empresa iworks", 35);
        ComplementoDTO complemento = new ComplementoDTO(155, "Avenida Paulista");
        ImagemDTO imagem = new ImagemDTO(155, "http://www.iworks.com.br/imagem1.jpg");

        rabbitTemplate.convertAndSend(exchangeCadastro, routerKeyCadastro, cadastro);
        rabbitTemplate.convertAndSend(exchangeCadastro, routerKeyComplemento, complemento);
        rabbitTemplate.convertAndSend(exchangeImagem, routerKeyImagem, imagem);
    }
}
