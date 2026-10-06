package com.bancoalimentar.Service;

import com.bancoalimentar.model.Alimento;
import com.bancoalimentar.model.Armazem;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import com.bancoalimentar.model.Alimento;
import com.bancoalimentar.model.Armazem;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ArmazemService {
    private final Armazem armazem = new Armazem();

    // GESTOR: valida se há espaço; devolve mensagem de erro ou null se houver espaço

    public String validarEspaco(List<Alimento> alimentos) {
        Map<Alimento.TipoAlimento, Double> pedidoPorTipo = new LinkedHashMap<>();
        for (Alimento alimento : alimentos) {
            if (alimento.getTipoAlimento() == null) {
                return "Alimento '" + alimento.getNomeAlimento() + "' sem tipo definido!";
            }
            if (alimento.getQuantidade() == null || alimento.getQuantidade() <= 0) {
                return "Alimento '" + alimento.getNomeAlimento() + "' com quantidade inválida!";
            }
            pedidoPorTipo.merge(alimento.getTipoAlimento(), alimento.getQuantidade(), Double::sum);
        }

        for (var entry : pedidoPorTipo.entrySet()) {
            if (!armazem.temEspaco(entry.getKey(), entry.getValue())) {
                return "Espaço insuficiente no armazém para " + entry.getKey()
                        + " (pedido: " + entry.getValue()
                        + ", livre: " + armazem.getLivre(entry.getKey()) + ")";
            }
        }
        return null;
    }

    // Ocupa espaço (chamado só depois de validado)
    public void ocupar(List<Alimento> alimentos) {
        for (Alimento alimento : alimentos) {
            armazem.ocupar(alimento.getTipoAlimento(), alimento.getQuantidade());
        }
    }

    // Adiciona espaço quando há saídas
    public void libertar(Alimento.TipoAlimento tipo, Double quantidade) {
        armazem.libertar(tipo, quantidade);
    }

    // Estado do armazém (por zona)
    public Map<String, Object> estado() {
        Map<String, Object> estado = new LinkedHashMap<>();
        for (Alimento.TipoAlimento tipo : Alimento.TipoAlimento.values()) {
            Map<String, Object> zona = new LinkedHashMap<>();
            zona.put("capacidade", armazem.getCapacidade(tipo));
            zona.put("ocupado", armazem.getOcupado(tipo));
            zona.put("livre", armazem.getLivre(tipo));
            estado.put(tipo.name(), zona);
        }
        return estado;
    }

    public void definirCapacidade(Alimento.TipoAlimento tipo, Double novaCapacidade) {
        armazem.setCapacidade(tipo, novaCapacidade);
    }

    //CONSTRUTOR STOCK INICIAL PRA TESTE DE SAIDA

        @Inject
    private AlimentoService alimentoService;

    // arrancar COM (20 unidades)
    @PostConstruct
    public void stockInicial() {
        // 10 enlatados
        Alimento atum = novoAlimento("Atum", "Postanova", "2028-05-12", Alimento.TipoAlimento.ENLATADOS, 10.0, 0.5);
        alimentoService.criar(atum);
        armazem.ocupar(Alimento.TipoAlimento.ENLATADOS, atum.getQuantidade());

        // 5 secos
        Alimento arroz = novoAlimento("Arroz", "Cristal", "2029-01-31", Alimento.TipoAlimento.SECOS, 5.0, 1.0);
        alimentoService.criar(arroz);
        armazem.ocupar(Alimento.TipoAlimento.SECOS, arroz.getQuantidade());

        // 5 perecíveis
        Alimento leite = novoAlimento("Leite", "Mimosa", "2026-12-15", Alimento.TipoAlimento.PERECIVEIS, 5.0, 1.0);
        alimentoService.criar(leite);
        armazem.ocupar(Alimento.TipoAlimento.PERECIVEIS, leite.getQuantidade());
    }

    // Helper para criar alimentos iniciais
    private Alimento novoAlimento(String nome, String marca, String validade,
                                  Alimento.TipoAlimento tipo, Double quantidade, Double pesoMedio) {
        Alimento a = new Alimento();
        a.setNomeAlimento(nome);
        a.setMarca(marca);
        a.setValidade(java.time.LocalDate.parse(validade));
        a.setTipoAlimento(tipo);
        a.setQuantidade(quantidade);
        a.setPesoMedio(pesoMedio);
        a.setOrigem(0L); // 0 = stock inicial (sem doação de origem)
        return a;
    }
}