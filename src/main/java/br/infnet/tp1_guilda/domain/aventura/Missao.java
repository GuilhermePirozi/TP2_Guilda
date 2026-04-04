package br.infnet.tp1_guilda.domain.aventura;

import br.infnet.tp1_guilda.domain.audit.Organization;
import br.infnet.tp1_guilda.domain.aventura.enums.NivelPerigo;
import br.infnet.tp1_guilda.domain.aventura.enums.StatusMissao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "missoes", schema = "aventura")
@Getter
@NoArgsConstructor
public class Missao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "missaoId")
    @SequenceGenerator(
            name = "missaoId",
            sequenceName = "missao_seq",
            allocationSize = 1,
            schema = "aventura"
    )
    @Column(name = "missao_id")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "organizacao_id", nullable = false)
    private Organization organizacao;

    @NotBlank(message = "O título da missão é obrigatório.")
    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @NotNull(message = "O nível de perigo é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_perigo", nullable = false)
    private NivelPerigo nivelPerigo;

    @NotNull(message = "O status da missão é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusMissao status;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private OffsetDateTime dataCriacao;

    @Column(name = "data_inicio")
    private OffsetDateTime dataInicio;

    @Column(name = "data_termino")
    private OffsetDateTime dataTermino;

    @OneToMany(mappedBy = "missao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipacaoMissao> participacoes = new ArrayList<>();

    public Missao(Organization organizacao, String titulo, NivelPerigo nivelPerigo, StatusMissao status) {
        this.organizacao = organizacao;
        this.titulo = titulo;
        this.nivelPerigo = nivelPerigo;
        this.status = status;
    }

    @PrePersist
    public void prePersist() {
        if (this.dataCriacao == null) {
            this.dataCriacao = OffsetDateTime.now();
        }
    }

    public void adicionarParticipacao(ParticipacaoMissao participacao) {
        participacoes.add(participacao);
    }

    public void alterarTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void alterarNivelPerigo(NivelPerigo nivelPerigo) {
        this.nivelPerigo = nivelPerigo;
    }

    public void alterarStatus(StatusMissao status) {
        this.status = status;
    }

    public void definirDataInicio(OffsetDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void definirDataTermino(OffsetDateTime dataTermino) {
        this.dataTermino = dataTermino;
    }
}