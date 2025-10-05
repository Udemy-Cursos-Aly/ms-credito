package com.aly.credito.listener;

import com.aly.credito.domain.Proposta;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropostaPendenteListener {

    @RabbitListener(queues = "${rabbit.mq.queue.pp.ms.credito}")
    public void consumerFilaPropostaPendenteAnaliseCredito(Proposta proposta) {}
}
