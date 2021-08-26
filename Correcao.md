### renaner123
- Disparou uma exceção ao tentar fazer um empréstimo com as seguintes entradas:
  - 1, 129, 2 e 2.
  - java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`renan`.`Emprestimo`, CONSTRAINT `fk_Emprestimo_Atividade1` FOREIGN KEY (`idAtividade`) REFERENCES `Atividade` (`idAtividade`))
  - O aluno 129 ficou como se estivesse emprestado algo, porém não aparece no relatório de empréstimos.
  - Finalizei o empréstimo do aluno 128 e depois entrei com os valores: 1, 128, 1 e 2. Novamente disparou a exceção:
  - java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`renan`.`Emprestimo`, CONSTRAINT `fk_Emprestimo_Atividade1` FOREIGN KEY (`idAtividade`) REFERENCES `Atividade` (`idAtividade`))
- Data da entrega: 22/08/2021
- Nota: 8

