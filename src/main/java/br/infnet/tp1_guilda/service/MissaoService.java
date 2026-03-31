package br.infnet.tp1_guilda.service;

import br.infnet.tp1_guilda.domain.aventura.Missao;
import br.infnet.tp1_guilda.domain.aventura.enums.NivelPerigo;
import br.infnet.tp1_guilda.domain.aventura.enums.StatusMissao;
import br.infnet.tp1_guilda.exceptions.BusinessException;
import br.infnet.tp1_guilda.repository.aventura.RepositoryMissao;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class MissaoService {

    private final RepositoryMissao repository;

    public MissaoService(RepositoryMissao repository) {
        this.repository = repository;
    }

    public Missao criar(Missao missao) {
        validarMissaoParaCriacao(missao);
        return repository.save(missao);
    }

    public Missao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Missão com id " + id + " não encontrada."));
    }

    public List<Missao> listarTodas() {
        return repository.findAll();
    }

    public Missao atualizarTitulo(Long id, String titulo) {
        Missao missao = buscarPorId(id);

        if (titulo == null || titulo.isBlank()) {
            throw new BusinessException("O título da missão é obrigatório.");
        }

        if (titulo.length() > 150) {
            throw new BusinessException("O título da missão deve ter no máximo 150 caracteres.");
        }

        missao.alterarTitulo(titulo);
        return repository.save(missao);
    }

    public Missao atualizarNivelPerigo(Long id, NivelPerigo nivelPerigo) {
        Missao missao = buscarPorId(id);

        if (nivelPerigo == null) {
            throw new BusinessException("O nível de perigo é obrigatório.");
        }

        missao.alterarNivelPerigo(nivelPerigo);
        return repository.save(missao);
    }

    public Missao atualizarStatus(Long id, StatusMissao status) {
        Missao missao = buscarPorId(id);

        if (status == null) {
            throw new BusinessException("O status da missão é obrigatório.");
        }

        missao.alterarStatus(status);
        return repository.save(missao);
    }

    public Missao definirDataInicio(Long id, OffsetDateTime dataInicio) {
        Missao missao = buscarPorId(id);

        missao.definirDataInicio(dataInicio);
        validarDatas(missao);

        return repository.save(missao);
    }

    public Missao definirDataTermino(Long id, OffsetDateTime dataTermino) {
        Missao missao = buscarPorId(id);

        missao.definirDataTermino(dataTermino);
        validarDatas(missao);

        return repository.save(missao);
    }

    public void remover(Long id) {
        Missao missao = buscarPorId(id);
        repository.delete(missao);
    }

    private void validarMissaoParaCriacao(Missao missao) {
        if (missao == null) {
            throw new BusinessException("A missão é obrigatória.");
        }

        if (missao.getOrganizacao() == null) {
            throw new BusinessException("A missão deve estar vinculada a uma organização.");
        }

        if (missao.getTitulo() == null || missao.getTitulo().isBlank()) {
            throw new BusinessException("O título da missão é obrigatório.");
        }

        if (missao.getTitulo().length() > 150) {
            throw new BusinessException("O título da missão deve ter no máximo 150 caracteres.");
        }

        if (missao.getNivelPerigo() == null) {
            throw new BusinessException("O nível de perigo é obrigatório.");
        }

        if (missao.getStatus() == null) {
            throw new BusinessException("O status da missão é obrigatório.");
        }

        validarDatas(missao);
    }

    private void validarDatas(Missao missao) {
        if (missao.getDataInicio() != null && missao.getDataTermino() != null
                && missao.getDataTermino().isBefore(missao.getDataInicio())) {
            throw new BusinessException("A data de término não pode ser anterior à data de início.");
        }
    }
}