package br.infnet.tp1_guilda.dto.consulta.missao;

import br.infnet.tp1_guilda.domain.aventura.enums.NivelPerigo;
import br.infnet.tp1_guilda.domain.aventura.enums.StatusMissao;

import java.time.OffsetDateTime;

public record FilterConsultaMissao(
        StatusMissao status,
        NivelPerigo nivelPerigo,
        OffsetDateTime dataCriacaoDe,
        OffsetDateTime dataCriacaoAte
) {
}
