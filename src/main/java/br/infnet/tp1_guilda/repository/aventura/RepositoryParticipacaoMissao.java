package br.infnet.tp1_guilda.repository.aventura;

import br.infnet.tp1_guilda.domain.aventura.ParticipacaoMissao;
import br.infnet.tp1_guilda.domain.aventura.ParticipacaoMissaoId;
import br.infnet.tp1_guilda.domain.aventura.enums.NivelPerigo;
import br.infnet.tp1_guilda.domain.aventura.enums.StatusMissao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface RepositoryParticipacaoMissao extends JpaRepository<ParticipacaoMissao, ParticipacaoMissaoId> {

    long countByAventureiro_Id(Long aventureiroId);

    @EntityGraph(attributePaths = {"missao"})
    Optional<ParticipacaoMissao> findFirstByAventureiro_IdOrderByDataRegistroDesc(Long aventureiroId);

    @Query("""
            select p.aventureiro.id as aventureiroId,
                   p.aventureiro.nome as nomeAventureiro,
                   count(p) as totalParticipacoes,
                   coalesce(sum(p.recompensaOuro), 0) as somaRecompensasOuro,
                   sum(case when p.destaque = true then 1 else 0 end) as quantidadeDestaques
            from ParticipacaoMissao p
            join p.missao m
            where p.dataRegistro >= :inicio
              and p.dataRegistro <= :fim
              and (:statusMissao is null or m.status = :statusMissao)
            group by p.aventureiro.id, p.aventureiro.nome
            """)
    List<RankingParticipacaoProj> rankingPorParticipacao(
            @Param("inicio") OffsetDateTime inicio,
            @Param("fim") OffsetDateTime fim,
            @Param("statusMissao") StatusMissao statusMissao
    );

    @Query("""
            select m.id as missaoId,
                   m.titulo as titulo,
                   m.status as status,
                   m.nivelPerigo as nivelPerigo,
                   count(p) as quantidadeParticipantes,
                   coalesce(sum(p.recompensaOuro), 0) as totalRecompensasDistribuidas
            from Missao m
            left join m.participacoes p
            where m.dataCriacao >= :inicio
              and m.dataCriacao <= :fim
            group by m.id, m.titulo, m.status, m.nivelPerigo
            """)
    List<MissaoMetricasProj> metricasPorMissaoNoPeriodo(
            @Param("inicio") OffsetDateTime inicio,
            @Param("fim") OffsetDateTime fim
    );

    interface RankingParticipacaoProj {
        Long getAventureiroId();

        String getNomeAventureiro();

        Long getTotalParticipacoes();

        Long getSomaRecompensasOuro();

        Long getQuantidadeDestaques();
    }

    interface MissaoMetricasProj {
        Long getMissaoId();

        String getTitulo();

        StatusMissao getStatus();

        NivelPerigo getNivelPerigo();

        Long getQuantidadeParticipantes();

        Long getTotalRecompensasDistribuidas();
    }
}