package br.infnet.tp1_guilda.dto.participacaoMissao;

import br.infnet.tp1_guilda.domain.aventura.enums.PapelMissao;

import java.time.OffsetDateTime;

public record ResponseParticipacaoMissao(
        Long missaoId,
        Long aventureiroId,
        PapelMissao papel,
        Integer recompensaOuro,
        boolean destaque,
        OffsetDateTime dataRegistro
) {
}
