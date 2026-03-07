package br.infnet.tp1_guilda.service;

import br.infnet.tp1_guilda.dto.AtualizarAventureiro;
import br.infnet.tp1_guilda.dto.DefinirCompanheiro;
import br.infnet.tp1_guilda.dto.FilterRequestAventureiro;
import br.infnet.tp1_guilda.dto.PageResult;
import br.infnet.tp1_guilda.exceptions.AventureiroNotFoundException;
import br.infnet.tp1_guilda.models.Aventureiro;
import br.infnet.tp1_guilda.models.Companheiro;
import br.infnet.tp1_guilda.repository.RepositoryAventureiro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceAventureiro {

    private final RepositoryAventureiro repositoryAventureiro;

    public Aventureiro criar(Aventureiro aventureiro) {
        return repositoryAventureiro.save(aventureiro);
    }

    public Aventureiro buscarPorId(Long id) {
        return repositoryAventureiro.findById(id)
                .orElseThrow(() -> new AventureiroNotFoundException(id));
    }

    public Aventureiro atualizar(Long id, AtualizarAventureiro update) {

        Aventureiro aventureiro = buscarPorId(id);

        if (update.nome() != null)
            aventureiro.alterarNome(update.nome());

        if (update.classe() != null)
            aventureiro.alterarClasse(update.classe());

        if (update.nivel() != null)
            aventureiro.alterarNivel(update.nivel());

        return repositoryAventureiro.save(aventureiro);
    }

    public Aventureiro encerrarVinculo(Long id) {
        Aventureiro aventureiro = buscarPorId(id);
        aventureiro.encerrarVinculo();
        return repositoryAventureiro.save(aventureiro);
    }

    public Aventureiro recrutarNovamente(Long id) {
        Aventureiro aventureiro = buscarPorId(id);
        aventureiro.recrutar();
        return repositoryAventureiro.save(aventureiro);
    }

    public Aventureiro removerCompanheiro(Long id) {
        Aventureiro aventureiro = buscarPorId(id);
        aventureiro.removerCompanheiro();
        return repositoryAventureiro.save(aventureiro);
    }

    public PageResult<Aventureiro> listar(FilterRequestAventureiro filtro, int page, int size) {
        return repositoryAventureiro.findWithFilter(filtro, page, size);
    }

    public Aventureiro definirCompanheiro(Long id, DefinirCompanheiro dto) {
        Aventureiro aventureiro = buscarPorId(id);

        Companheiro companheiro = new Companheiro(
                dto.nome(),
                dto.especie(),
                dto.lealdade()
        );

        aventureiro.definirCompanheiro(companheiro);

        return repositoryAventureiro.save(aventureiro);
    }
}