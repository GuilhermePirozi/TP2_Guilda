package br.infnet.tp1_guilda.mapper;

import br.infnet.tp1_guilda.domain.aventura.Aventureiro;
import br.infnet.tp1_guilda.dto.CriarAventureiro;
import br.infnet.tp1_guilda.dto.ResponseAventureiro;
import org.springframework.stereotype.Component;

@Component
public class MapperAventureiro {
    public Aventureiro toEntity(CriarAventureiro dto) {
        return new Aventureiro(
                dto.nome(),
                dto.classe(),
                dto.nivel()
        );
    }

    public ResponseAventureiro toResponse(Aventureiro aventureiro) {
        return new ResponseAventureiro(
                aventureiro.getId(),
                aventureiro.getNome(),
                aventureiro.getClasse(),
                aventureiro.getNivel(),
                aventureiro.getAtivo()
        );
    }
}