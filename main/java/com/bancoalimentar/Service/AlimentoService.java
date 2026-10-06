package com.bancoalimentar.Service;

import com.bancoalimentar.model.Alimento;

import jakarta.enterprise.context.ApplicationScoped;


import java.util.ArrayList;
import java.util.List;


@ApplicationScoped 
public class AlimentoService {
    private List<Alimento> alimentos = new ArrayList<>();
    private Long proximoId = 1L;
    
    // Métodos CRUD
    public Alimento criar(Alimento alimento) {
        alimento.setId(proximoId++);
        alimentos.add(alimento);
        return alimento;
    }
    
    public List<Alimento> listar() {
        return alimentos;
    }
    
    public Alimento buscarPorId(Long id) {
        return alimentos.stream()
        .filter(a -> a.getId().equals(id))
        .findFirst()
        .orElse(null);
    }
    
    public Alimento atualizar(Long id, Alimento alimentoAtualizado) {
        Alimento alimento = buscarPorId(id);
        if (alimento == null) {
            throw new RuntimeException("Alimento não encontrado!");
        }
        alimento.setNomeAlimento(alimentoAtualizado.getNomeAlimento());
        alimento.setMarca(alimentoAtualizado.getMarca());
        alimento.setValidade(alimentoAtualizado.getValidade());
        alimento.setOrigem(alimentoAtualizado.getOrigem());
        return alimento;
    }
    
    
    public void remover(Long id) {
        alimentos.removeIf(a -> a.getId().equals(id));
    }
    
    // Métodos de consulta por validade
    public List<Alimento> listarVencidos() {
        return alimentos.stream()
        .filter(Alimento::estaVencido)
        .toList();
    }
    
    public List<Alimento> listarValidos() {
        return alimentos.stream()
        .filter(a -> !a.estaVencido())
        .toList();
    }
    
    // Métodos de consulta por origem
    public List<Alimento> listarPorOrigem(Long origemId) {
        return alimentos.stream()
        .filter(a -> a.getOrigem().equals(origemId))
        .toList();
    }
}