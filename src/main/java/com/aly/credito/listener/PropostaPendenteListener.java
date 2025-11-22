package com.aly.credito.listener;

import com.aly.credito.domain.Proposta;
import com.aly.credito.service.AnaliseCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PropostaPendenteListener {
    private final AnaliseCreditoService analiseCreditoService;

    @RabbitListener(queues = "${rabbit.mq.queue.pp.ms.credito}")
    public void consumerFilaPropostaPendenteAnaliseCredito(Proposta proposta) {
        this.analiseCreditoService.analisarCredito(proposta);
    }
}
