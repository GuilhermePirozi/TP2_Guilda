package br.infnet.tp1_guilda.mapper;

import br.infnet.tp1_guilda.domain.aventura.Companheiro;
import br.infnet.tp1_guilda.dto.companheiro.DefinirCompanheiro;
import br.infnet.tp1_guilda.dto.companheiro.ResponseCompanheiro;
import org.springframework.stereotype.Component;

@Component
public class MapperCompanheiro {

    public Companheiro toEntity(DefinirCompanheiro dto) {
        return new Companheiro(
                dto.nome(),
                dto.especie(),
                dto.lealdade()
        );
    }

    public ResponseCompanheiro toResponse(Companheiro companheiro) {
        int lealdade = companheiro.getLealdade() != null ? companheiro.getLealdade() : 0;
        Long aventureiroId = companheiro.getAventureiro() != null
                ? companheiro.getAventureiro().getId()
                : companheiro.getId();
        return new ResponseCompanheiro(
                aventureiroId,
                companheiro.getNome(),
                companheiro.getEspecie(),
                lealdade
        );
    }
}
