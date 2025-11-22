package com.aly.credito.strategy.impl;

import com.aly.credito.domain.Proposta;
import com.aly.credito.strategy.ICalculoPontos;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NegativadoSerasa implements ICalculoPontos {
    @Override
    public int calcular(Proposta proposta) {
        if (nomeNegativadoSerasa()) {
            return -200;
        }

        return 100;
    }

    private boolean nomeNegativadoSerasa() {
        // @TODO: Implementar client para se comunicar com o Serasa utilizando o Mockoon
        return new Random().nextBoolean();
    }
}
