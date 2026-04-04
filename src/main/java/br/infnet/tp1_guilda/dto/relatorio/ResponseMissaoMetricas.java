package br.infnet.tp1_guilda.dto.relatorio;

import br.infnet.tp1_guilda.domain.aventura.enums.NivelPerigo;
import br.infnet.tp1_guilda.domain.aventura.enums.StatusMissao;

public record ResponseMissaoMetricas(
        Long missaoId,
        String titulo,
        StatusMissao status,
        NivelPerigo nivelPerigo,
        long quantidadeParticipantes,
        long totalRecompensasDistribuidas
) {
}
