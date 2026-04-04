package br.infnet.tp1_guilda.mapper;

import br.infnet.tp1_guilda.dto.relatorio.ResponseMissaoMetricas;
import br.infnet.tp1_guilda.dto.relatorio.ResponseRankingParticipacao;
import br.infnet.tp1_guilda.repository.aventura.RepositoryParticipacaoMissao.MissaoMetricasProj;
import br.infnet.tp1_guilda.repository.aventura.RepositoryParticipacaoMissao.RankingParticipacaoProj;
import org.springframework.stereotype.Component;

@Component
public class MapperRelatorio {

    public ResponseRankingParticipacao toRankingParticipacao(RankingParticipacaoProj linha) {
        return new ResponseRankingParticipacao(
                linha.getAventureiroId(),
                linha.getNomeAventureiro(),
                zeroSeNulo(linha.getTotalParticipacoes()),
                zeroSeNulo(linha.getSomaRecompensasOuro()),
                zeroSeNulo(linha.getQuantidadeDestaques())
        );
    }

    public ResponseMissaoMetricas toMissaoMetricas(MissaoMetricasProj linha) {
        return new ResponseMissaoMetricas(
                linha.getMissaoId(),
                linha.getTitulo(),
                linha.getStatus(),
                linha.getNivelPerigo(),
                zeroSeNulo(linha.getQuantidadeParticipantes()),
                zeroSeNulo(linha.getTotalRecompensasDistribuidas())
        );
    }

    private static long zeroSeNulo(Long n) {
        return n == null ? 0L : n;
    }
}
