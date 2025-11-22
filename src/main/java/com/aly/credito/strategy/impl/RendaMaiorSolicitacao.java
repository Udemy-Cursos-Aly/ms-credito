package com.aly.credito.strategy.impl;

import com.aly.credito.domain.Proposta;
import com.aly.credito.strategy.ICalculoPontos;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class RendaMaiorSolicitacao implements ICalculoPontos {
    @Override
    public int calcular(Proposta proposta) {
        if (proposta.getUsuario().getRenda() < proposta.getValorSolicitado()) {
            return 30;
        }

        return 100;
    }
}
