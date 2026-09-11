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

Movimentos (entrada/saída) devem ser registrados para rastreabilidade.


- Registo de entidades beneficiarias e Entidades Doadoras

- Catalogação de items em inventario

- Gestão de inventario a base de LIFO

-Confirmação de elegibilidade para Saida ( validação dos componentes de saídas com o inventario)

- Notificar as entidades responsáveis relativamente a Entradas/Saidas

- Arquivo de entradas e saídas numa base de dados 

- ARQUIVAR REGISTOS DIARIOS A 00:00?




 