package com.aly.credito.constants;

public enum EnumMensagens {
    PROPOSTA_MENSAGEM_APROVADA("Parabéns 🥳 Prezado(a) %s. Sua proposta foi aprovada."),
    PROPOSTA_MENSAGEM_RECUSADA("Temos uma má notícia... ☹️ Prezado(a) %s, sua proposta foi recusada por sua pontuação ser muito baixa.");

    private final String mensagem;

    EnumMensagens(final String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
