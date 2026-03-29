package br.infnet.tp1_guilda.repository;

import br.infnet.tp1_guilda.domain.aventura.Aventureiro;
import br.infnet.tp1_guilda.dto.FilterRequestAventureiro;
import br.infnet.tp1_guilda.dto.PageResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAventureiro extends JpaRepository<Aventureiro, Long> {
    PageResult<Aventureiro> findWithFilter(
            FilterRequestAventureiro filtro,
            int page,
            int size
    );
}