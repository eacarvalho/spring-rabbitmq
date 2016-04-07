package br.com.iworks.core.listener.service;

import org.springframework.amqp.core.Message;

import java.io.UnsupportedEncodingException;

public interface ImagemListenerService {
    void receive(Message mensagem) throws UnsupportedEncodingException;
}
