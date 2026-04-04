package br.infnet.tp1_guilda.dto.missao;

import br.infnet.tp1_guilda.domain.aventura.enums.NivelPerigo;
import br.infnet.tp1_guilda.domain.aventura.enums.StatusMissao;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record AtualizarMissao(
        @Size(max = 150, message = "O título da missão deve ter no máximo 150 caracteres.")
        String titulo,
        NivelPerigo nivelPerigo,
        StatusMissao status,
        OffsetDateTime dataInicio,
        OffsetDateTime dataTermino
) {
}
