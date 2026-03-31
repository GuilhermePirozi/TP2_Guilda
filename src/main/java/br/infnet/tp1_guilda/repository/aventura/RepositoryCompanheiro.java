package br.infnet.tp1_guilda.repository.aventura;

import br.infnet.tp1_guilda.domain.aventura.Companheiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCompanheiro extends JpaRepository<Companheiro, Long> {
}
