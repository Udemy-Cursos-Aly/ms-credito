package com.aly.credito.strategy.impl;

import com.aly.credito.domain.Proposta;
import com.aly.credito.strategy.ICalculoPontos;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmprestimosAndamento implements ICalculoPontos {
    @Override
    public int calcular(Proposta proposta) {
        if (buscarEmprestimosEmAndamento()) {
            return -80;
        }

        return 80;
    }

    private boolean buscarEmprestimosEmAndamento() {
        // @TODO: Implementar comunicação com o Bacen via Mockoon
        return new Random().nextBoolean();
    }
}
