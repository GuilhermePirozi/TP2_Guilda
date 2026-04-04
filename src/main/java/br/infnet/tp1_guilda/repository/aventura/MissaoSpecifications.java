package br.infnet.tp1_guilda.repository.aventura;

import br.infnet.tp1_guilda.domain.aventura.Missao;
import br.infnet.tp1_guilda.dto.consulta.missao.FilterConsultaMissao;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class MissaoSpecifications {

    private MissaoSpecifications() {
    }

    public static Specification<Missao> comFiltro(FilterConsultaMissao filtro) {
        return (root, query, cb) -> {
            List<Predicate> partes = new ArrayList<>();
            if (filtro.status() != null) {
                partes.add(cb.equal(root.get("status"), filtro.status()));
            }
            if (filtro.nivelPerigo() != null) {
                partes.add(cb.equal(root.get("nivelPerigo"), filtro.nivelPerigo()));
            }
            if (filtro.dataCriacaoDe() != null && filtro.dataCriacaoAte() != null) {
                partes.add(cb.between(root.get("dataCriacao"), filtro.dataCriacaoDe(), filtro.dataCriacaoAte()));
            } else if (filtro.dataCriacaoDe() != null) {
                partes.add(cb.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.dataCriacaoDe()));
            } else if (filtro.dataCriacaoAte() != null) {
                partes.add(cb.lessThanOrEqualTo(root.get("dataCriacao"), filtro.dataCriacaoAte()));
            }
            if (partes.isEmpty()) {
                return cb.conjunction();
            }
            return cb.and(partes.toArray(Predicate[]::new));
        };
    }
}
