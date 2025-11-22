package com.aly.credito.service;

import com.aly.credito.constants.EnumMensagens;
import com.aly.credito.domain.Proposta;
import com.aly.credito.strategy.ICalculoPontos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnaliseCreditoService {
    private final List<ICalculoPontos> strategy;
    private final NotificacaoService notificacaoService;

    public void analisarCredito(Proposta proposta) {
        boolean aprovado = isAprovado(proposta);
        proposta.setAprovado(aprovado);

        EnumMensagens mensagemParaFormartacao = getEnumMensagemParaObservacao(aprovado);
        String mensagemParaObservacao = formatarMensagem(mensagemParaFormartacao, proposta.getUsuario().getNome());

        proposta.setObservacao(mensagemParaObservacao);

        notificacaoService.enviarNotificacaoParaFila(proposta);
    }

    private boolean isAprovado(Proposta proposta) {
        int pontos = 0;
        for (ICalculoPontos calculoPontos : strategy) {
            pontos += calculoPontos.calcular(proposta);
        }

        return pontos < 350;
    }

    private String formatarMensagem(EnumMensagens mensagem, String nomeUsuario) {
        return String.format(mensagem.getMensagem(), nomeUsuario);
    }

    private EnumMensagens getEnumMensagemParaObservacao(boolean aprovado) {
        return aprovado ? EnumMensagens.PROPOSTA_MENSAGEM_APROVADA : EnumMensagens.PROPOSTA_MENSAGEM_RECUSADA;
    }
}
