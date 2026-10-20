# [Banco Alimentar]
 
# Description

Breve descrição do sistema e do problema que resolve:

Projeto de gestão de inventario de um banco alimentar que gere o inventário de um banco alimentar. Este sistema irá e registar todas as entradas e saídas do banco alimentar na sua rotina.

Link Jira : https://cafetcg.atlassian.net/jira/core/projects/PMDC/board?filter=&groupBy=none




## Main Entities (Data Base)

- *Entidades (Doadora + Beneficiária):id , nome , loc , 

- item - mone , id , categoria , loc (a.b) , data entrada, status

- entrada/saída - id, componentes , origem , entidade beneficiária.

- (ex: Entidades / Histórico):
 



## Main Business Rules

-Doações pendentes devem ser validadas antes de serem aceite (verificar validade e compatibilidade).

-Espaço no armazém é finito, não é possível adicionar alimentos se não houver espaço.

-Alimentos expirados não podem ser aceites (devem ser rejeitados, dai validação humana# calcular validade MENOS tempo de viagem estimado= AINDA EM VALIDADE?#).

- Movimentos (entrada/saída) devem ser registrados para rastreabilidade.


- Registo de entidades beneficiarias e Entidades Doadoras

- Catalogação de items em inventario

- Gestão de inventario a base de LIFO

-Confirmação de elegibilidade para Saida ( validação dos componentes de saídas com o inventario)

- Notificar as entidades responsáveis relativamente a Entradas/Saidas

- Arquivo de entradas e saídas numa base de dados 



## Entidade

Representa qualquer pessoa/organização registada no sistema.

Propriedades: id, nome, morada, contacto, email, tipo

TipoEntidade (enum): DOADOR ou BENEFICIARIO — define o papel da entidade e é validado nos services.


## Alimento

Cada item de alimento no inventário.

Propriedades: id, nomeAlimento, marca, validade (LocalDate), quantidade, pesoMedio, tipoAlimento, origem

TipoAlimento (enum): SECOS, PERECIVEIS, ENLATADOS, MATERNAL, OUTROS — determina a zona do armazém onde fica.

Origem: o ID da doação que o trouxe (0 = stock inicial do seed) — permite rastrear a proveniência.

Tem método auxiliar removerQuantidade() para decrementar o stock nas saídas.


## Doacao


O registo de uma doação feita por um doador.

Propriedades: id, doadorId, conteudos (List<Alimento> — pode ter varios alimentos), data


## Saida

O registo de uma entrega a um beneficiário.

Propriedades: id, alimentoId, beneficiarioId, quantidade, data

Relações: a um Alimento e a um Entidade do tipo BENEFICIARIO.

## Armazem

Gestor do espaço físico, dividido em zonas.

Propriedades: por cada TipoAlimento — capacidade, ocupado, livre (iniciam a 100 por zona)

Métodos: ocupar(), libertar(), validarEspaco() — a inteligência de espaço vive aqui.

## Services 

EntidadeService — CRUD de entidades (listar, criar, buscarPorId, atualizar, remover). Inclui o seed @PostConstruct que cria o doador de teste (Pedro, ID 1).

AlimentoService — CRUD de alimentos + consultas específicas: validos(), vencidos(), porOrigem() (usa a validade e a origem).

DoacaoService — o fluxo mais rico:
criar(): valida que o doador existe e é DOADOR → consulta o gestor do armazém (validarEspaco, somando os alimentos por tipo) → se houver espaço, cria a doação, adiciona os alimentos ao inventário (marcando a origem) e ocupa o armazém.

remover(): remoção em cascata e coerente — remove os alimentos ainda existentes no inventário e liberta apenas o espaço que efetivamente ocupavam (não a quantidade original, porque parte pode já ter sido entregue).

atualizar(), listarPorDoador().

SaidaService — 
criar(): valida que o alimento existe, que o beneficiário existe e é BENEFICIARIO, e que a quantidade é válida → decrementa o stock, remove o alimento se chegar a 0, e liberta o espaço no armazém.

ArmazemService (o gestor) — o guardião do espaço:

validarEspaco(): soma as quantidades por tipo e devolve mensagem de erro se exceder o livre.

ocupar() / libertar(): ajustam as zonas.

estado(): expõe capacidade/ocupado/livre por zona.



