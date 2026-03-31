package br.infnet.tp1_guilda.domain.aventura;

import br.infnet.tp1_guilda.domain.aventura.enums.PapelMissao;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Entity
@Table(name = "participacoes_missao", schema = "aventura")
@Getter
@NoArgsConstructor
public class ParticipacaoMissao {

    @EmbeddedId
    private ParticipacaoMissaoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("missaoId")
    @JoinColumn(name = "missao_id", nullable = false)
    private Missao missao;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("aventureiroId")
    @JoinColumn(name = "aventureiro_id", nullable = false)
    private Aventureiro aventureiro;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "papel", nullable = false)
    private PapelMissao papel;

    @Min(0)
    @Column(name = "recompensa_ouro")
    private Integer recompensaOuro;

    @NotNull
    @Column(name = "destaque", nullable = false)
    private Boolean destaque;

    @Column(name = "data_registro", nullable = false, updatable = false)
    private OffsetDateTime dataRegistro;

    public ParticipacaoMissao(Missao missao, Aventureiro aventureiro,
                              PapelMissao papel, Integer recompensaOuro, Boolean destaque) {
        this.missao = missao;
        this.aventureiro = aventureiro;
        this.papel = papel;
        this.recompensaOuro = recompensaOuro;
        this.destaque = destaque;
        this.id = new ParticipacaoMissaoId(missao.getId(), aventureiro.getId());
    }

    @PrePersist
    public void prePersist() {
        if (this.dataRegistro == null) {
            this.dataRegistro = OffsetDateTime.now();
        }
    }
}