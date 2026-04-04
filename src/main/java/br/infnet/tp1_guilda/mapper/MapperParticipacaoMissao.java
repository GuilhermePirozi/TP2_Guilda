package br.infnet.tp1_guilda.mapper;

import br.infnet.tp1_guilda.domain.aventura.ParticipacaoMissao;
import br.infnet.tp1_guilda.dto.participacaoMissao.ResponseParticipacaoMissao;
import org.springframework.stereotype.Component;

@Component
public class MapperParticipacaoMissao {

    public ResponseParticipacaoMissao toResponse(ParticipacaoMissao p) {
        return new ResponseParticipacaoMissao(
                p.getId().getMissaoId(),
                p.getId().getAventureiroId(),
                p.getPapel(),
                p.getRecompensaOuro(),
                Boolean.TRUE.equals(p.getDestaque()),
                p.getDataRegistro()
        );
    }
}
