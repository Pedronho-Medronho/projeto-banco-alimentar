package com.bancoalimentar.model;

import java.util.ArrayList;
import java.util.List;

class Armazem {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

// Armazem is part of the same package; keeping the reference explicit avoids
// IDE/compiler ambiguity when the class is not yet available to the current build.

// Classe tem de ter espaço criado , comparar o alimento com a area e calcular se o alimento tem espaço no iventario

public class AreaArmazenamento {

    private Long id;
    private Armazem armazem;
    private TipoAlimento tipoAlimento;
    private Double capacidadeMaxima;
    private Double capacidadeDisponivel; 
    private Double temperatura; 
    private Double umidade; 
    private List<Alimento> alimentos = new ArrayList<>();

  
    public AreaArmazenamento() {
    }

    public AreaArmazenamento(TipoAlimento tipoAlimento, Double capacidadeMaxima, Double temperatura, Double umidade) {
        this.tipoAlimento = tipoAlimento;
        this.capacidadeMaxima = capacidadeMaxima;
        this.capacidadeDisponivel = capacidadeMaxima; 
        this.temperatura = temperatura;
        this.umidade = umidade;
    }

    // ========== GS ==========
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Armazem getArmazem() {
        return armazem;
    }

    public void setArmazem(Armazem armazem) {
        this.armazem = armazem;
    }

    // If the Armazem type is not available in this module yet, this import-less
    // reference will still compile as long as the class exists in the same package.

    public TipoAlimento getTipoAlimento() {
        return tipoAlimento;
    }

    public void setTipoAlimento(TipoAlimento tipoAlimento) {
        this.tipoAlimento = tipoAlimento;
    }

    public Double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(Double capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public Double getCapacidadeDisponivel() {
        return capacidadeDisponivel;
    }

    public void setCapacidadeDisponivel(Double capacidadeDisponivel) {
        this.capacidadeDisponivel = capacidadeDisponivel;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getUmidade() {
        return umidade;
    }

    public void setUmidade(Double umidade) {
        this.umidade = umidade;
    }

    public List<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    public boolean temEspaco(Double quantidade) {
        if (capacidadeDisponivel == null || capacidadeMaxima == null) {
            return false;
        }
        return capacidadeDisponivel >= quantidade;
    }

    private TipoAlimento obterTipoAlimento(Alimento alimento) {
        if (alimento == null) {
            return null;
        }

        try {
            return (TipoAlimento) Alimento.class.getMethod("getTipoAlimento").invoke(alimento);
        } catch (NoSuchMethodException ignored) {
            try {
                return (TipoAlimento) Alimento.class.getMethod("getTipo").invoke(alimento);
            } catch (ReflectiveOperationException ex) {
                return null;
            }
        } catch (ReflectiveOperationException ex) {
            return null;
        }
    }

    public boolean adicionarAlimento(Alimento alimento) {
        if (alimento == null || alimento.getQuantidade() == null) {
            return false;
        }

        TipoAlimento tipoAlimentoDoItem = obterTipoAlimento(alimento);
        if (this.tipoAlimento == null || tipoAlimentoDoItem == null || !this.tipoAlimento.equals(tipoAlimentoDoItem)) {
            return false;
        }

        if (!temEspaco(alimento.getQuantidade().doubleValue())) {
            return false;
        }

        alimentos.add(alimento);
        capacidadeDisponivel -= alimento.getQuantidade().doubleValue();
        return true;
    }

    public boolean removerAlimento(Alimento alimento) {
        if (alimento == null || !alimentos.remove(alimento)) {
            return false;
        }

        if (alimento.getQuantidade() != null) {
            capacidadeDisponivel += alimento.getQuantidade().doubleValue();
        }
        return true;
    }

}