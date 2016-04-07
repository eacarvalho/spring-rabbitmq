package br.com.iworks.core.listener.service;

import org.springframework.amqp.core.Message;

import java.io.UnsupportedEncodingException;

public interface CadastroListenerService {
    void receive(Message mensagem) throws UnsupportedEncodingException;
}
