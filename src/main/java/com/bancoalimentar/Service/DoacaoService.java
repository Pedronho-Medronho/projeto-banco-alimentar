package com.bancoalimentar.Service;

import com.bancoalimentar.model.Doacao;
import com.bancoalimentar.model.Entidade;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DoacaoService {
    private List<Doacao> doacoes = new ArrayList<>();
    private Long proximoId = 1L;

    @Inject
    private AlimentoService alimentoService;

    @Inject
    private EntidadeService entidadeService;

    public List<Doacao> listar() {
        return doacoes;
    }

    public Doacao buscarPorId(Long id) {
        return doacoes.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Get por doador

    public List<Doacao> listarPorDoador(Long doadorId) {
        return doacoes.stream()
                .filter(d -> d.getDoadorId().equals(doadorId))
                .toList();
    }

        // Remove a doação  alimentos da doação saem do inventário e o armazém liberta o espaço correspondente
    public void remover(Long id) {
        Doacao doacao = buscarPorId(id);
        if (doacao == null) {
            throw new RuntimeException("Doação não encontrada!");
        }

        // 1. Remove os alimentos da doação do inventário e liberta o armazém
        for (var alimento : doacao.getConteudos()) {
            var existente = alimentoService.buscarPorId(alimento.getId());
            if (existente != null && existente.getQuantidade() > 0) {
                armazemService.libertar(existente.getTipoAlimento(), existente.getQuantidade());
                alimentoService.remover(existente.getId());
            }
        }

        doacoes.removeIf(d -> d.getId().equals(id));
    }

    // Métodos CRUD
    public Doacao atualizar(Long id, Doacao doacaoAtualizada) {
        Doacao doacao = buscarPorId(id);
        if (doacao == null) {
            return null;
        }
        doacao.setDoadorId(doacaoAtualizada.getDoadorId());
        doacao.setConteudos(doacaoAtualizada.getConteudos());
        return doacao;
    }

    @Inject
    private ArmazemService armazemService;

        // Verificador do ENUM Doador
    public Doacao criar(Doacao doacao) {
        var doador = entidadeService.buscarPorId(doacao.getDoadorId());
        if (doador == null) {
            throw new RuntimeException("Doador não encontrado!");
        }
        if (doador.getTipo() != Entidade.TipoEntidade.DOADOR) {
            throw new RuntimeException("A entidade com ID " + doacao.getDoadorId()
                    + " não é um DOADOR!");
        }

        
        // GESTOR DO ARMAZÉM: verifica espaço ANTES de aceitar
        String erro = armazemService.validarEspaco(doacao.getConteudos());
        if (erro != null) {
            throw new RuntimeException(erro);
        }

        doacao.setId(proximoId++);
        doacoes.add(doacao);

        for (var alimento : doacao.getConteudos()) {
            alimento.setOrigem(doacao.getId());
            alimentoService.criar(alimento);
        }

        armazemService.ocupar(doacao.getConteudos());
        return doacao;
    }
}