package br.infnet.tp1_guilda.dto.consulta.aventureiro;

import br.infnet.tp1_guilda.dto.companheiro.ResponseCompanheiro;
import br.infnet.tp1_guilda.enums.Classe;

public record ResponseAventureiroPerfil(
        Long id,
        Long organizacaoId,
        String nome,
        Classe classe,
        int nivel,
        boolean ativo,
        ResponseCompanheiro companheiro,
        long totalParticipacoesMissao,
        UltimaMissaoParticipacaoResumo ultimaParticipacao
) {
}
