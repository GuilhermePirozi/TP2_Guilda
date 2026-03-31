package br.infnet.tp1_guilda.service;

import br.infnet.tp1_guilda.domain.aventura.*;
import br.infnet.tp1_guilda.domain.aventura.enums.PapelMissao;
import br.infnet.tp1_guilda.domain.aventura.enums.StatusMissao;
import br.infnet.tp1_guilda.exceptions.BusinessException;
import br.infnet.tp1_guilda.repository.aventura.RepositoryParticipacaoMissao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipacaoMissaoService {

    private final RepositoryParticipacaoMissao repository;

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

        return repository.save(participacao);
    }

    public void removerParticipacao(Long missaoId, Long aventureiroId) {

        ParticipacaoMissaoId id = new ParticipacaoMissaoId(missaoId, aventureiroId);

        if (!repository.existsById(id)) {
            throw new BusinessException("Participação não encontrada.");
        }

        repository.deleteById(id);
    }
}