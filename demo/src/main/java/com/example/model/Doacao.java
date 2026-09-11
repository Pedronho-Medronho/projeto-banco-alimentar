package com.bancoalimentar.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doacao {
    private Long id;
    private Doador doador;
    private List<Alimento> alimentos = new ArrayList<>();
    private LocalDate dataDoacao;
    private Double volumeTotal; 
    private EstadoDoacao estado;
    private String notas;

    public Doacao() {
    }

    public Doacao(Doador doador, List<Alimento> alimentos) {
        this.doador = doador;
        this.alimentos = alimentos;
        this.dataDoacao = LocalDate.now(); 
        this.volumeTotal = calcularVolumeTotal();
        this.estado = EstadoDoacao.PENDENTE;
    }

    public Double calcularVolumeTotal() {
        return this.alimentos.stream()
        // ##################PENSAR EM ALTERNATIVA AO LAMBDA##################
                .mapToDouble(alimento -> alimento.getVolumePorUnidade() * alimento.getQuantidade())
                .sum();
    }

    public boolean validarAlimentos() {
        return this.alimentos.stream()
                // ##################PENSAR EM ALTERNATIVA AO LAMBDA##################

                .allMatch(alimento -> !alimento.estaVencido());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    public List<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<Alimento> alimentos) {
        this.alimentos = alimentos;
        this.volumeTotal = calcularVolumeTotal(); 
    }

    public LocalDate getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(LocalDate dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public Double getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(Double volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public EstadoDoacao getEstado() {
        return estado;
    }

    public void setEstado(EstadoDoacao estado) {
        this.estado = estado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Doacao{" +
                "id=" + id +
                ", doador=" + (doador != null ? doador.getNome() : "null") +
                ", alimentos=" + alimentos.size() +
                ", dataDoacao=" + dataDoacao +
                ", volumeTotal=" + volumeTotal +
                ", estado=" + estado +
                ", notas='" + notas + '\'' +
                '}';
    }
}