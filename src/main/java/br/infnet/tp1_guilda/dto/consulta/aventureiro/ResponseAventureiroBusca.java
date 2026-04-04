package br.infnet.tp1_guilda.dto.consulta.aventureiro;

import br.infnet.tp1_guilda.enums.Classe;

public record ResponseAventureiroBusca(
        Long id,
        String nome,
        Classe classe,
        int nivel,
        boolean ativo
) {
}
