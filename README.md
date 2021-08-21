## Sistema para empréstimos de equipamentos e kit de ensino

<!--ts-->
   * [Projeto proposto](#Projeto-proposto-e-solução)
   * [Funcionalidades implementadas](#Funcionalidades-implementadas) 
   * [Regras de négocios implementadas](#Regras-de-négocios-implementadas)
   * [Digrama ER](#Diagrama-ER)
   * [Instruções para compilar e executar o projeto](#Instruções-para-compilar-e-executar-o-projeto)

   * [Instruções para fazer emprestimos](#Instruções-para-fazer-emprestimos)


<!--te-->

## Projeto proposto

O projeto proposto pode ser visto neste [arquivo](img/projeto1.pdf).

## Funcionalidades implementadas

- [x] Efetuar emprestimo
- [x] Renovar emprestimo
- [x] Finalizar emprestimo
- [x] Relatório sobre todos os empréstimos em andamento
- [x] Relatório sobre todos os alunos que já emprestaram um determinado equipamento;
- [x] Relatório sobretodos os equipamentos emprestados por um determinado aluno
- [x] Relatório sobre empréstimos emandamento e que estão vencidos
- [x] Relatório de todos equipamentos cadastrados no banco
- [x] Relatório de todos kits cadastrados no banco
- [x] Relatório de todos alunos cadastrados no banco
- [x] Banco de dados MySQL.
- [x] Aplicação Java desktop
- [x] Usar database.properties dentro de resources. 
- [x] Modelagem do banco de dados é relacional
- [x] Conjunto de instruções DDL e DML para criar e povoar a base de dados com nome ddl-dml.sql na raiz do projeto.

## Regras de négocios implementadas
- [x] Somente alunos ativos no curso e sem débitos podem fazer empestimo;
- [x] Aluno pode fazer somente 1 emprestimo por vez;
- [ ] 1 Emprestimo pode ter registrados vários equipamentos ou kits. -> Na minha implementação aluno só pode emprestar 1 equipamento ou 1 kit;
- [x] Prazo para o empréstimo depende da atividade na qual o aluno usará o equipamento;
- [x] Ao aluno é dada a possibilidade de renovar um empréstimo somente antes do mesmo vencer
- [x] Aluno pode renovar um emprestimo por no máximo 3 vezes;
- [ ] Reserva para o emprestimo/equipamento;
- [x] Para atividades de ensino, a data de entrega, mesmo para as renovações, não deve ultrapassar o último dia letivo do semestre
- [x] Para as atividades de pesquisa,extensão e TCC o aluno poderá fazer uma renovação de forma que continue com o item emprestado durante as férias acadêmicas.

## Diagrama ER

<div style="text-align:center">
   <img src="modelagem.png" />
</div>

## Instruções para compilar e executar o projeto

Pra compilar o projeto, é necessário ir ao diretório raiz do projeto e usar o comando:

```
   gradlew shadowJar
```
Caso ocorra tudo certo, irá retornar a mensagem BUILD SUCCESSFUL e será gerado o arquivo projeto-bcd-01-1.0-SNAPSHOT-all.jar em [projeto-bcd-01/build/libs](projeto-bcd-01/build/libs). Agora para executar é só usar o comando abaixo:

```shell
    java -jar build/libs/projeto-bcd-01-1.0-SNAPSHOT-all.jar
```
Se preferir também pode executar com:

```shell
   java -cp build/libs/projeto-bcd-01-1.0-SNAPSHOT-all.jar bcd.Principal
```

O projeto em si já possui um arquivo .jar que foi gerado pela IDE e se encontram em [projeto-bcd-01/out/arifacts](projeto-bcd-01/out/artifacts). 
Para executar a classe Principal da pasta raiz será necessário usar o comando abaixo 

```shell
   java -jar out/artifacts/projeto_bcd_01_main_jar/projeto-bcd-01.main.jar
```

## Instruções para fazer emprestimos

Aqui estão alguns exemplos de emprestimos para tesar as funcionalidades implementadas. As instruções que vão funcionar possui *sucesso*, as que não podem ser feitas tem *falha* nos exemplos abaixo.


* Aluno com matricula 129 não possui emprestimo e é ativo no curso. Use essa matricula e um id de atividade 500, para efetuar um emprestimo; *Sucesso*, gera data de devolução para 15 dias.
  
* Aluno com matricula 123 já possui emprestimo e está atrasado. Use essa matricula para tentar renovar o emprestimo 700; *Falha*, já passou da data de devolução.

* Emprestimo 700 está atrasado. Use essa id para finalizar o emprestimo do aluno 123; *Sucesso*. Irá gerar penalidade para o aluno.

* Aluno com matricula 123 não possui emprestimo, mas está com penalidade. Use essa matricula e um id de atividade entre(500,501 e 502) para efetuar um emprestimo; *Falha*, aluno está com penalidade.

* Aluno com matricula 128 tem emprestimo e não está atrasado. Use essa matricula para tentar renovar o emprestimo 708. Sucesso.

* Aluno com matricula 128 tem emprestimo e não está atrasado. Use essa matricula para tentar renovar o emprestimo 708. Sucesso.

* Aluno com matricula 128 tem emprestimo e não está atrasado. Use essa matricula para tentar renovar o emprestimo 708; *falha*, aluno já renovou 3 vezes esse emprestimo.

* Aluno com matricula 126 tem emprestimo e não está atrasado. Use essa matricula para finalizar o emprestimo 703; *sucesso*, aluno está em dia, não gerou penalidade.

* Aluno com matricula 127 não possui emprestimo e não é ativo no curso. Use essa matricula para tentar fazer um emprestimo; *falha*, aluno não está ativo no curso.

* Aluno com matricula 125 possui emprestimo e é ativo no curso. Use essa matricula para tentar fazer um emprestimo; *falha*, aluno já possui um emprestimo vigente

* Aluno com matricula 131 não possui emprestimo e é ativo no curso. Use essa matricula para fazer um emprestimo com id de atividade 501; *sucesso*, gera data de devolução sendo o fim do semestre.

