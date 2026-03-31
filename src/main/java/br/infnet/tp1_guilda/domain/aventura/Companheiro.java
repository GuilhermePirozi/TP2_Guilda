package br.infnet.tp1_guilda.domain.aventura;

import br.infnet.tp1_guilda.enums.Especie;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "companheiros", schema = "aventura")
@Getter
@Setter
@NoArgsConstructor
public class Companheiro {

    @Id
    @Column(name = "aventureiro_id")
    private Long id;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "aventureiro_id")
    private Aventureiro aventureiro;

    @NotBlank(message = "O nome não pode ser vazio")
    @Column(name = "nome", nullable = false, length = 120)
    private String nome;

    @NotNull(message = "A espécie é obrigatória")
    @Enumerated(EnumType.STRING)
    @Column(name = "especie", nullable = false)
    private Especie especie;

    @NotNull
    @Range(min = 0, max = 100, message = "A lealdade deve ser entre 0 e 100")
    @Column(name = "lealdade", nullable = false)
    private Integer lealdade;

    public Companheiro(String nome, Especie especie, Integer lealdade) {
        this.nome = nome;
        this.especie = especie;
        this.lealdade = lealdade;
    }

}