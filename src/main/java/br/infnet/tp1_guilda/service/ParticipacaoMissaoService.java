package br.infnet.tp1_guilda.service;

import br.infnet.tp1_guilda.domain.aventura.Aventureiro;
import br.infnet.tp1_guilda.domain.aventura.Missao;
import br.infnet.tp1_guilda.domain.aventura.ParticipacaoMissao;
import br.infnet.tp1_guilda.domain.aventura.ParticipacaoMissaoId;
import br.infnet.tp1_guilda.domain.aventura.enums.PapelMissao;
import br.infnet.tp1_guilda.domain.aventura.enums.StatusMissao;
import br.infnet.tp1_guilda.dto.participacaoMissao.CriarParticipacaoMissao;
import br.infnet.tp1_guilda.exceptions.AventureiroNotFoundException;
import br.infnet.tp1_guilda.exceptions.BusinessException;
import br.infnet.tp1_guilda.exceptions.MissaoNotFoundException;
import br.infnet.tp1_guilda.exceptions.ParticipacaoMissaoNotFoundException;
import br.infnet.tp1_guilda.repository.aventura.RepositoryAventureiro;
import br.infnet.tp1_guilda.repository.aventura.RepositoryMissao;
import br.infnet.tp1_guilda.repository.aventura.RepositoryParticipacaoMissao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipacaoMissaoService {

    private final RepositoryParticipacaoMissao repository;
    private final RepositoryMissao repositoryMissao;
    private final RepositoryAventureiro repositoryAventureiro;

    @Transactional
    public ParticipacaoMissao participar(Long missaoId, Long aventureiroId, CriarParticipacaoMissao dto) {
        Missao missao = repositoryMissao.findById(missaoId).orElseThrow(() -> new MissaoNotFoundException(missaoId));
        Aventureiro aventureiro = repositoryAventureiro.findById(aventureiroId).orElseThrow(() -> new AventureiroNotFoundException(aventureiroId));
        return participar(missao, aventureiro, dto.papel(), dto.recompensaOuro(), dto.destaque());
    }

    @Transactional
    public ParticipacaoMissao participar(
            Missao missao,
            Aventureiro aventureiro,
            PapelMissao papel,
            Integer recompensaOuro,
            Boolean destaque
    ) {

        if (!aventureiro.getAtivo()) {
            throw new BusinessException("Aventureiro inativo não pode participar de missões.");
        }

        if (!missao.getOrganizacao().equals(aventureiro.getOrganizacao())) {
            throw new BusinessException("O aventureiro não pertence à mesma organização da missão.");
        }

        if (missao.getStatus() != StatusMissao.PLANEJADA &&
                missao.getStatus() != StatusMissao.EM_ANDAMENTO) {
            throw new BusinessException("A missão não aceita participantes no estado atual.");
        }

        ParticipacaoMissaoId id = new ParticipacaoMissaoId(
                missao.getId(),
                aventureiro.getId()
        );

        if (repository.existsById(id)) {
            throw new BusinessException("O aventureiro já está participando dessa missão.");
        }

        ParticipacaoMissao participacao = new ParticipacaoMissao(
                missao,
                aventureiro,
                papel,
                recompensaOuro,
                destaque
        );

        missao.adicionarParticipacao(participacao);

        return participacao;
    }

    @Transactional
    public void removerParticipacao(Long missaoId, Long aventureiroId) {

        ParticipacaoMissao participacao = repository.findById(
                new ParticipacaoMissaoId(missaoId, aventureiroId)
        ).orElseThrow(() -> new ParticipacaoMissaoNotFoundException(missaoId, aventureiroId));

        participacao.getMissao().getParticipacoes().remove(participacao);
    }
}
