package br.infnet.tp1_guilda.dto.consulta.missao;

import br.infnet.tp1_guilda.domain.aventura.enums.NivelPerigo;
import br.infnet.tp1_guilda.domain.aventura.enums.StatusMissao;

import java.time.OffsetDateTime;
import java.util.List;

public record ResponseMissaoDetalhe(
        Long id,
        Long organizacaoId,
        String titulo,
        NivelPerigo nivelPerigo,
        StatusMissao status,
        OffsetDateTime dataCriacao,
        OffsetDateTime dataInicio,
        OffsetDateTime dataTermino,
        List<ResponseParticipanteMissao> participantes
) {
}
