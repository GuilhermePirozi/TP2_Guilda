package br.infnet.tp1_guilda.repository.aventura;

import br.infnet.tp1_guilda.domain.aventura.Missao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryMissao extends JpaRepository<Missao, Long> {
}
