package br.infnet.tp1_guilda.dto;

import br.infnet.tp1_guilda.enums.Classe;

public record ResponseAventureiro(
        Long id,
        String nome,
        Classe classe,
        int nivel,
        boolean ativo
) { }