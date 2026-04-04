package br.infnet.tp1_guilda.repository.aventura;

import br.infnet.tp1_guilda.domain.aventura.Missao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RepositoryMissao extends JpaRepository<Missao, Long>, JpaSpecificationExecutor<Missao> {

    @Query("""
            select distinct m from Missao m
            left join fetch m.participacoes p
            left join fetch p.aventureiro
            where m.id = :id
            """)
    Optional<Missao> findDetalhadoById(@Param("id") Long id);
}
