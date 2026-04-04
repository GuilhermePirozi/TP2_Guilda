package br.infnet.tp1_guilda.dto.aventureiro;

import br.infnet.tp1_guilda.dto.companheiro.ResponseCompanheiro;
import br.infnet.tp1_guilda.enums.Classe;

public record ResponseAventureiro(
        Long id,
        String nome,
        Classe classe,
        int nivel,
        boolean ativo,
        ResponseCompanheiro companheiro
) {
}
