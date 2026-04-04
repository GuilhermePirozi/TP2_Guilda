package br.infnet.tp1_guilda.dto.participacaoMissao;

import br.infnet.tp1_guilda.domain.aventura.enums.PapelMissao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CriarParticipacaoMissao(
        @NotNull(message = "O papel na missão é obrigatório.")
        PapelMissao papel,
        @Min(value = 0, message = "A recompensa em ouro não pode ser negativa.")
        Integer recompensaOuro,
        @NotNull(message = "O campo destaque é obrigatório.")
        Boolean destaque
) {
}
