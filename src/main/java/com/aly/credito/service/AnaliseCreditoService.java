package com.aly.credito.service;

import com.aly.credito.constants.EnumMensagens;
import com.aly.credito.domain.Proposta;
import com.aly.credito.strategy.ICalculoPontos;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AnaliseCreditoService {
    private final List<ICalculoPontos> strategy;
    private final NotificacaoService notificacaoService;

    public void analisarCredito(Proposta proposta) {
        log.info("Início da análise de crédito: {} - {}", LocalDateTime.now(), proposta);
        boolean aprovado = isAprovado(proposta);
        proposta.setAprovada(aprovado);

        EnumMensagens mensagemParaFormartacao = getEnumMensagemParaObservacao(aprovado);
        String mensagemParaObservacao = formatarMensagem(mensagemParaFormartacao, proposta.getUsuario().getNome());

        proposta.setObservacao(mensagemParaObservacao);

        log.info("Fim da análise de crédito: {} - {}", LocalDateTime.now(), proposta);
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
