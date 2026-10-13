package com.bancoalimentar.model;

import java.util.EnumMap;
import java.util.Map;

public class Armazem {
    private final Map<Alimento.TipoAlimento, Double> capacidade = new EnumMap<>(Alimento.TipoAlimento.class);
    private final Map<Alimento.TipoAlimento, Double> ocupado = new EnumMap<>(Alimento.TipoAlimento.class);

    // Construtor com espaços alocados por tipo (em unidades)
    public Armazem() {
        for (Alimento.TipoAlimento tipo : Alimento.TipoAlimento.values()) {
            capacidade.put(tipo, 100.0); // capacidade default por zona, ajustável
            ocupado.put(tipo, 0.0);
        }
    }

    // Métodos de negócio
    public Double getCapacidade(Alimento.TipoAlimento tipo) {
        return capacidade.get(tipo);
    }

    public Double getOcupado(Alimento.TipoAlimento tipo) {
        return ocupado.get(tipo);
    }

    public Double getLivre(Alimento.TipoAlimento tipo) {
        return capacidade.get(tipo) - ocupado.get(tipo);
    }

    public boolean temEspaco(Alimento.TipoAlimento tipo, Double quantidade) {
        return quantidade <= getLivre(tipo);
    }

    public void ocupar(Alimento.TipoAlimento tipo, Double quantidade) {
        ocupado.put(tipo, ocupado.get(tipo) + quantidade);
    }

    public void libertar(Alimento.TipoAlimento tipo, Double quantidade) {
        ocupado.put(tipo, Math.max(0, ocupado.get(tipo) - quantidade));
    }

    public void setCapacidade(Alimento.TipoAlimento tipo, Double novaCapacidade) {
        capacidade.put(tipo, novaCapacidade);
    }
}