package com.bancoalimentar.Service;

import com.bancoalimentar.model.Saida;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SaidaService {
    private List<Saida> saidas = new ArrayList<>();
    private Long proximoId = 1L;

    @Inject
    private AlimentoService alimentoService;

    @Inject
    private EntidadeService entidadeService;

    @Inject
    private ArmazemService armazemService;

    // Registra a saída e decrementa a quantidade do inventário
    public Saida criar(Saida saida) {
        var alimento = alimentoService.buscarPorId(saida.getAlimentoId());
        if (alimento == null) {
            throw new RuntimeException("Alimento não existe no inventário!");
        }

        var beneficiario = entidadeService.buscarPorId(saida.getBeneficiarioId());
        if (beneficiario == null) {
            throw new RuntimeException("Beneficiário não encontrado!");
        }

        if (saida.getQuantidade() == null || saida.getQuantidade() <= 0
                || saida.getQuantidade() > alimento.getQuantidade()) {
            throw new RuntimeException("Quantidade inválida ou superior ao inventário!");
        }

        saida.setId(proximoId++);
        saidas.add(saida);

        alimento.removerQuantidade(saida.getQuantidade());

                armazemService.libertar(alimento.getTipoAlimento(), saida.getQuantidade());
                saida.getQuantidade();

        if (alimento.getQuantidade() <= 0) {
            alimentoService.remover(alimento.getId());
        }

        return saida;
    }

    public List<Saida> listar() {
        return saidas;
    }

    public Saida buscarPorId(Long id) {
        return saidas.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Métodos de consulta por beneficiário
    public List<Saida> listarPorBeneficiario(Long beneficiarioId) {
        return saidas.stream()
                .filter(s -> s.getBeneficiarioId().equals(beneficiarioId))
                .toList();
    }

    public void remover(Long id) {
        saidas.removeIf(s -> s.getId().equals(id));
    }
}