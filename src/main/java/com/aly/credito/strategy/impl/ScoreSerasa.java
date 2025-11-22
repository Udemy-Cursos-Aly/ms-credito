package com.aly.credito.strategy.impl;

import com.aly.credito.domain.Proposta;
import com.aly.credito.strategy.ICalculoPontos;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(2)
@Component
public class ScoreSerasa implements ICalculoPontos {
    @Override
    public int calcular(Proposta proposta) {
        int scoreDoUsuario = score();
        if (scoreDoUsuario <= 200) {
            return -30;
        }

        return 150;
    }

    private int score() {
        // @TODO: Implementar client para se comunicar com o Serasa utilizando o Mockoon
        return new Random().nextInt(0, 1000);
    }
}
