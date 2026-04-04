package br.infnet.tp1_guilda.mapper;

import br.infnet.tp1_guilda.domain.audit.Organization;
import br.infnet.tp1_guilda.domain.audit.User;
import br.infnet.tp1_guilda.domain.aventura.Aventureiro;
import br.infnet.tp1_guilda.dto.aventureiro.CriarAventureiro;
import br.infnet.tp1_guilda.dto.aventureiro.ResponseAventureiro;
import br.infnet.tp1_guilda.dto.consulta.aventureiro.ResponseAventureiroBusca;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperAventureiro {

    private final MapperCompanheiro mapperCompanheiro;

    public Aventureiro toEntity(Organization organizacao, User user, CriarAventureiro dto) {
        return new Aventureiro(
                organizacao,
                user,
                dto.nome(),
                dto.classe(),
                dto.nivel()
        );
    }

    public ResponseAventureiroBusca toBusca(Aventureiro aventureiro) {
        return new ResponseAventureiroBusca(
                aventureiro.getId(),
                aventureiro.getNome(),
                aventureiro.getClasse(),
                aventureiro.getNivel(),
                Boolean.TRUE.equals(aventureiro.getAtivo())
        );
    }

    public ResponseAventureiro toResponse(Aventureiro aventureiro) {
        var companheiro = aventureiro.getCompanheiro() != null
                ? mapperCompanheiro.toResponse(aventureiro.getCompanheiro())
                : null;
        return new ResponseAventureiro(
                aventureiro.getId(),
                aventureiro.getNome(),
                aventureiro.getClasse(),
                aventureiro.getNivel(),
                aventureiro.getAtivo(),
                companheiro
        );
    }
}
