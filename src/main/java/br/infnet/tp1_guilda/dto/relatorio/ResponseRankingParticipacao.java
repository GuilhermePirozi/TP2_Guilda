package br.infnet.tp1_guilda.dto.relatorio;

public record ResponseRankingParticipacao(
        Long aventureiroId,
        String nomeAventureiro,
        long totalParticipacoes,
        long somaRecompensasOuro,
        long quantidadeDestaques
) {
}
