package br.infnet.tp1_guilda.mapper;

import br.infnet.tp1_guilda.domain.audit.Organization;
import br.infnet.tp1_guilda.domain.aventura.Missao;
import br.infnet.tp1_guilda.dto.consulta.missao.ResponseMissaoDetalhe;
import br.infnet.tp1_guilda.dto.consulta.missao.ResponseParticipanteMissao;
import br.infnet.tp1_guilda.dto.missao.AtualizarMissao;
import br.infnet.tp1_guilda.dto.missao.CriarMissao;
import br.infnet.tp1_guilda.dto.missao.ResponseMissao;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class MapperMissao {

    public Missao toEntity(Organization organizacao, CriarMissao dto) {
        return new Missao(organizacao, dto.titulo(), dto.nivelPerigo(), dto.status());
    }

    public ResponseMissao toResponse(Missao missao) {
        Long orgId = missao.getOrganizacao() != null ? missao.getOrganizacao().getId() : null;
        return new ResponseMissao(
                missao.getId(),
                orgId,
                missao.getTitulo(),
                missao.getNivelPerigo(),
                missao.getStatus(),
                missao.getDataCriacao(),
                missao.getDataInicio(),
                missao.getDataTermino()
        );
    }

    public ResponseMissaoDetalhe toDetalhe(Missao missao) {
        List<ResponseParticipanteMissao> participantes = List.of();
        if (missao.getParticipacoes() != null && !missao.getParticipacoes().isEmpty()) {
            participantes = missao.getParticipacoes().stream()
                    .map(p -> new ResponseParticipanteMissao(
                            p.getAventureiro().getId(),
                            p.getAventureiro().getNome(),
                            p.getPapel(),
                            p.getRecompensaOuro(),
                            Boolean.TRUE.equals(p.getDestaque())
                    ))
                    .sorted(Comparator.comparing(ResponseParticipanteMissao::nomeAventureiro))
                    .toList();
        }
        Long orgId = missao.getOrganizacao() != null ? missao.getOrganizacao().getId() : null;
        return new ResponseMissaoDetalhe(
                missao.getId(),
                orgId,
                missao.getTitulo(),
                missao.getNivelPerigo(),
                missao.getStatus(),
                missao.getDataCriacao(),
                missao.getDataInicio(),
                missao.getDataTermino(),
                participantes
        );
    }

    public void aplicarAtualizacao(Missao missao, AtualizarMissao dto) {
        if (dto.titulo() != null) {
            missao.alterarTitulo(dto.titulo());
        }
        if (dto.nivelPerigo() != null) {
            missao.alterarNivelPerigo(dto.nivelPerigo());
        }
        if (dto.status() != null) {
            missao.alterarStatus(dto.status());
        }
        if (dto.dataInicio() != null) {
            missao.definirDataInicio(dto.dataInicio());
        }
        if (dto.dataTermino() != null) {
            missao.definirDataTermino(dto.dataTermino());
        }
    }
}
