package br.infnet.tp1_guilda.domain.aventura;

import br.infnet.tp1_guilda.enums.Especie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Getter
@ToString
public class Companheiro {
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;
    @NotNull(message = "A espécie é obrigatória")
    private Especie especie;
    @NotNull
    @Range(min = 0, max = 100, message = "A lealdade deve ser um inteiro entre 0 e 100")
    private Integer lealdade;

    public Companheiro(String nome, Especie especie, Integer lealdade) {
        this.nome = nome;
        this.especie = especie;
        this.lealdade = lealdade;
    }
}