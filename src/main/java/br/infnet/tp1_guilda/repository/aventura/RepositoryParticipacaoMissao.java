package br.infnet.tp1_guilda.repository.aventura;

import br.infnet.tp1_guilda.domain.aventura.ParticipacaoMissao;
import br.infnet.tp1_guilda.domain.aventura.ParticipacaoMissaoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryParticipacaoMissao extends JpaRepository<ParticipacaoMissao, ParticipacaoMissaoId> {
}