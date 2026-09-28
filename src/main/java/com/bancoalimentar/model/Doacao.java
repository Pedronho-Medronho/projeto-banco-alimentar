package com.bancoalimentar.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doacao {
    private Long id;
    private Long doadorId;
    private List<Alimento> conteudos = new ArrayList<>();
    private LocalDate data;

    // Construtor
    public Doacao() {
        this.data = LocalDate.now();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoadorId() {
        return doadorId;
    }

    public void setDoadorId(Long doadorId) {
        this.doadorId = doadorId;
    }

    public List<Alimento> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<Alimento> conteudos) {
        this.conteudos = conteudos;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    // Métodos de negócio
    public int getTotalAlimentos() {
        return conteudos.size();
    }
}