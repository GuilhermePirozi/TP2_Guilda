package br.infnet.tp1_guilda.dto.companheiro;

import br.infnet.tp1_guilda.enums.Especie;

public record ResponseCompanheiro(
        Long aventureiroId,
        String nome,
        Especie especie,
        int lealdade
) {
}
