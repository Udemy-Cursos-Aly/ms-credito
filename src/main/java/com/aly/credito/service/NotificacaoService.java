package com.aly.credito.service;

import com.aly.credito.domain.Proposta;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoService {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.mq.pc.exchange}")
    private String exchange;

    public void enviarNotificacaoParaFila(Proposta proposta) {
        this.rabbitTemplate.convertAndSend(exchange, "", proposta);
    }
}
