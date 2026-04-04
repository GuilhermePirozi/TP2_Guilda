package br.infnet.tp1_guilda.dto.companheiro;

import br.infnet.tp1_guilda.enums.Especie;
import org.hibernate.validator.constraints.Range;

public record AtualizarCompanheiro(
        String nome,
        Especie especie,
        @Range(min = 0, max = 100, message = "A lealdade deve ser um inteiro entre 0 e 100")
        Integer lealdade
) {
}
