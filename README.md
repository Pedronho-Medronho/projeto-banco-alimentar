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

-Alimentos expirados não podem ser aceites 

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

TipoEntidade (enum): DOADOR ou BENEFICIARIO 


## Alimento

Cada item de alimento no inventário.

Propriedades: id, nomeAlimento, marca, validade (LocalDate), quantidade, pesoMedio, tipoAlimento, origem

TipoAlimento (enum): SECOS, PERECIVEIS, ENLATADOS, MATERNAL, OUTROS 

Origem: o ID da doação que o trouxe 



## Doacao


O registo de uma doação feita por um doador.

Propriedades: id, doadorId, conteudos (List<Alimento> — pode ter varios alimentos), data


## Saida

O registo de uma entrega a um beneficiário.

Propriedades: id, alimentoId, beneficiarioId, quantidade, data

Relações: a um Alimento e a um Entidade do tipo BENEFICIARIO.

## Armazem

Gestor do espaço físico, dividido em zonas.

Propriedades: por cada TipoAlimento

Métodos: ocupar(), libertar(), validarEspaco() 

## Services 

EntidadeService — CRUD de entidades (listar, criar, buscarPorId, atualizar, remover). Inclui o seed @PostConstruct que cria o doador de teste (Pedro, ID 1).

AlimentoService — CRUD de alimentos + consultas específicas: validos(), vencidos(), porOrigem() (usa a validade e a origem).

DoacaoService — o fluxo mais rico:
criar(): valida que o doador existe e é DOADOR 

remover(): remoção em cascata e coerente 
atualizar(), listarPorDoador().

SaidaService — 
criar(): valida que o alimento existe, que o beneficiário existe e é BENEFICIARIO

## ArmazemService 

validarEspaco(): 

ocupar() / libertar(): ajustam as zonas.

estado(): expõe capacidade/ocupado/livre por zona.



