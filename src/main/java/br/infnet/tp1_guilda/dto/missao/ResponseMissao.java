package br.infnet.tp1_guilda.dto.missao;

import br.infnet.tp1_guilda.domain.aventura.enums.NivelPerigo;
import br.infnet.tp1_guilda.domain.aventura.enums.StatusMissao;

import java.time.OffsetDateTime;

public record ResponseMissao(
        Long id,
        Long organizacaoId,
        String titulo,
        NivelPerigo nivelPerigo,
        StatusMissao status,
        OffsetDateTime dataCriacao,
        OffsetDateTime dataInicio,
        OffsetDateTime dataTermino
) {
}
