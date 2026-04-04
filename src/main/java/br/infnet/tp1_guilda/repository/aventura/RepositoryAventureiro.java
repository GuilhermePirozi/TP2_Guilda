package br.infnet.tp1_guilda.repository.aventura;

import br.infnet.tp1_guilda.domain.aventura.Aventureiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryAventureiro extends JpaRepository<Aventureiro, Long>, JpaSpecificationExecutor<Aventureiro> {
}