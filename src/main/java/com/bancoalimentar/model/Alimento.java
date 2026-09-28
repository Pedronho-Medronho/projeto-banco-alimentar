package com.bancoalimentar.model;

import java.time.LocalDate;

public class Alimento {
    private Long id;
    private String nomeAlimento;
    private String marca;
    private LocalDate validade;
    private Long origem; // id da doação origem

    // Construtor
    public Alimento() {
        this.validade = LocalDate.now();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAlimento() {
        return nomeAlimento;
    }

    public void setNomeAlimento(String nomeAlimento) {
        this.nomeAlimento = nomeAlimento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Long getOrigem() {
        return origem;
    }

    public void setOrigem(Long origem) {
        this.origem = origem;
    }

    // Quantidade alimentos 

    private Double quantidade;

    // (dentro de Getters e Setters)
    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

  
    public void adicionarQuantidade(Double valor) {
        this.quantidade += valor;
    }

    public void removerQuantidade(Double valor) {
        this.quantidade -= valor;
    }

    

  // Check validade alimento
    public boolean estaVencido() {
        return LocalDate.now().isAfter(this.validade);
    }
}