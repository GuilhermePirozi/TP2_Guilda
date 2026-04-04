package br.infnet.tp1_guilda.controllers;

import br.infnet.tp1_guilda.domain.aventura.enums.StatusMissao;
import br.infnet.tp1_guilda.dto.relatorio.ResponseMissaoMetricas;
import br.infnet.tp1_guilda.dto.relatorio.ResponseRankingParticipacao;
import br.infnet.tp1_guilda.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping("/ranking-participacao")
    public ResponseEntity<List<ResponseRankingParticipacao>> rankingParticipacao(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fim,
            @RequestParam(required = false) StatusMissao statusMissao
    ) {
        return ResponseEntity.ok(relatorioService.rankingParticipacao(inicio, fim, statusMissao));
    }

    @GetMapping("/missoes-metricas")
    public ResponseEntity<List<ResponseMissaoMetricas>> missoesMetricas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fim
    ) {
        return ResponseEntity.ok(relatorioService.metricasMissoesNoPeriodo(inicio, fim));
    }
}
