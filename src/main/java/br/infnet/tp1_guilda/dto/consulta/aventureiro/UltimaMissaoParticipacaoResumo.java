package br.infnet.tp1_guilda.dto.consulta.aventureiro;

import java.time.OffsetDateTime;

public record UltimaMissaoParticipacaoResumo(
        Long missaoId,
        String tituloMissao,
        OffsetDateTime dataRegistroParticipacao
) {
}
