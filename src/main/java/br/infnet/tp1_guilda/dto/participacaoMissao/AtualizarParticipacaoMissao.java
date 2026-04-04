package br.infnet.tp1_guilda.dto.participacaoMissao;

import br.infnet.tp1_guilda.domain.aventura.enums.PapelMissao;
import jakarta.validation.constraints.Min;

public record AtualizarParticipacaoMissao(
        PapelMissao papel,
        @Min(value = 0, message = "A recompensa em ouro não pode ser negativa.")
        Integer recompensaOuro,
        Boolean destaque
) {
}
