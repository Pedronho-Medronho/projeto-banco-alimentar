package com.bancoalimentar.Service;

import com.bancoalimentar.model.Entidade;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;


@ApplicationScoped

public class EntidadeService {
    private List<Entidade> entidades = new ArrayList<>();
    private Long proximoId = 1L;

    public Entidade criar(Entidade entidade) {
        entidade.setId(proximoId++);
        entidades.add(entidade);
        return entidade;
    }

    public List<Entidade> listar() {
        return entidades;
    }

        // Método de busca por ID
    public Entidade buscarPorId(Long id) {
        return entidades.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
  
    public Entidade atualizar(Long id, Entidade entidadeAtualizada) {
        Entidade entidade = buscarPorId(id);
        if (entidade == null) {
            return null;
        }
        entidade.setNome(entidadeAtualizada.getNome());
        entidade.setMorada(entidadeAtualizada.getMorada());
        entidade.setContacto(entidadeAtualizada.getContacto());
        entidade.setTipo(entidadeAtualizada.getTipo());
        return entidade;
    }

    public void remover(Long id) {
        entidades.removeIf(e -> e.getId().equals(id));
    }

}