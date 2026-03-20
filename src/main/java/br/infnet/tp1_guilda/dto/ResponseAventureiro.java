package br.infnet.tp1_guilda.dto;

import br.infnet.tp1_guilda.enums.Classe;
import br.infnet.tp1_guilda.domain.aventureiro.Aventureiro;

public record ResponseAventureiro(
        Long id,
        String nome,
        Classe classe,
        int nivel,
        boolean ativo
) {
    public ResponseAventureiro(Aventureiro aventureiro) {
        this(
                aventureiro.getId(),
                aventureiro.getNome(),
                aventureiro.getClasse(),
                aventureiro.getNivel(),
                aventureiro.getAtivo()
        );
    }
}