package br.infnet.tp1_guilda.dto.missao;

import br.infnet.tp1_guilda.domain.aventura.enums.NivelPerigo;
import br.infnet.tp1_guilda.domain.aventura.enums.StatusMissao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CriarMissao(
        @NotNull(message = "A organização é obrigatória.")
        Long organizacaoId,
        @NotBlank(message = "O título da missão é obrigatório.")
        @Size(max = 150, message = "O título da missão deve ter no máximo 150 caracteres.")
        String titulo,
        @NotNull(message = "O nível de perigo é obrigatório.")
        NivelPerigo nivelPerigo,
        @NotNull(message = "O status da missão é obrigatório.")
        StatusMissao status
) {
}
