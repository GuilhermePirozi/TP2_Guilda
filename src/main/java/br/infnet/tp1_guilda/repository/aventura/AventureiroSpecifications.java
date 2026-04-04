package br.infnet.tp1_guilda.repository.aventura;

import br.infnet.tp1_guilda.domain.aventura.Aventureiro;
import br.infnet.tp1_guilda.dto.aventureiro.FilterRequestAventureiro;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class AventureiroSpecifications {

    private AventureiroSpecifications() {
    }

    public static Specification<Aventureiro> comFiltro(FilterRequestAventureiro filtro) {
        return (root, query, cb) -> {
            List<Predicate> partes = new ArrayList<>();
            if (filtro.classe() != null) {
                partes.add(cb.equal(root.get("classe"), filtro.classe()));
            }
            if (filtro.ativo() != null) {
                partes.add(cb.equal(root.get("ativo"), filtro.ativo()));
            }
            if (filtro.nivelMinimo() != null) {
                partes.add(cb.ge(root.get("nivel"), filtro.nivelMinimo()));
            }
            if (partes.isEmpty()) {
                return cb.conjunction();
            }
            return cb.and(partes.toArray(Predicate[]::new));
        };
    }

    public static Specification<Aventureiro> nomeContem(String trecho) {
        return (root, query, cb) -> {
            String padrao = "%" + trecho.toLowerCase(Locale.ROOT) + "%";
            return cb.like(cb.lower(root.get("nome")), padrao);
        };
    }
}
