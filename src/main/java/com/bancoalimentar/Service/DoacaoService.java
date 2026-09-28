package com.bancoalimentar.Service;

import com.bancoalimentar.model.Doacao;
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

    public Doacao criar(Doacao doacao) {
        var doador = entidadeService.buscarPorId(doacao.getDoadorId());
        if (doador == null) {
            throw new RuntimeException("Doador não encontrado!");
        }

        doacao.setId(proximoId++);
        doacoes.add(doacao);

        for (var alimento : doacao.getConteudos()) {
            alimento.setOrigem(doacao.getId());
            alimentoService.criar(alimento);
        }

        return doacao;
    }

    public List<Doacao> listar() {
        return doacoes;
    }

    public Doacao buscarPorId(Long id) {
        return doacoes.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    //Get por doador
    
    public List<Doacao> listarPorDoador(Long doadorId) {
        return doacoes.stream()
                .filter(d -> d.getDoadorId().equals(doadorId))
                .toList();
    }

    public void remover(Long id) {
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
}