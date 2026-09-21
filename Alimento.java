package com.bancoalimentar.model;

import java.time.LocalDate;

public class Alimento {
    private Long id;
    private String nome;
    private TipoAlimento tipo;
    private Double pesoPorUnidade;
    private Integer quantidade;
    private LocalDate dataValidade;
    private LocalDate dataRecebimento;

    // Construtor
    public Alimento() {
    }

    public Alimento(String nome, TipoAlimento tipo, Double pesoPorUnidade, Integer quantidade, LocalDate dataValidade) {
        this.nome = nome;
        this.tipo = tipo;
        this.pesoPorUnidade = pesoPorUnidade;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
        this.dataRecebimento = LocalDate.now();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoAlimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAlimento tipo) {
        this.tipo = tipo;
    }

    public Double getPesoPorUnidade() {
        return pesoPorUnidade;
    }

    public void setPesoPorUnidade(Double pesoPorUnidade) {
        this.pesoPorUnidade = pesoPorUnidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public LocalDate getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(LocalDate dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    // Métodos de negócio
    public Double getPesoTotal() {
        return this.pesoPorUnidade * this.quantidade;
    }

    public boolean estaVencido() {
        return LocalDate.now().isAfter(this.dataValidade);
    }
}

enum TipoAlimento {
    FRUTA,
    LEGUME,
    CEREAL,
    LACTEO,
    OUTRO
}