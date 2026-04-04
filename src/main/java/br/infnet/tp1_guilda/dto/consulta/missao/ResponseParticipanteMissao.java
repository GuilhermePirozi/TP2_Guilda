package br.infnet.tp1_guilda.dto.consulta.missao;

import br.infnet.tp1_guilda.domain.aventura.enums.PapelMissao;

public record ResponseParticipanteMissao(
        Long aventureiroId,
        String nomeAventureiro,
        PapelMissao papel,
        Integer recompensaOuro,
        boolean destaque
) {
}
