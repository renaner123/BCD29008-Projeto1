-- MySQL Script generated by MySQL Workbench
-- Fri Aug 20 16:15:31 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Projeto1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Curso` (
  `idCurso` INT NOT NULL AUTO_INCREMENT,
  `nomeCurso` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCurso`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Alunos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Alunos` (
  `matricula` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(45) NOT NULL,
  `situacao` TINYINT NOT NULL DEFAULT 0,
  `Curso_idCurso` INT NOT NULL,
  `temEmprestimo` TINYINT NULL,
  `penalidade` INT NULL,
  PRIMARY KEY (`matricula`),
  INDEX `fk_Alunos_Curso1_idx` (`Curso_idCurso` ASC) VISIBLE,
  CONSTRAINT `fk_Alunos_Curso1`
    FOREIGN KEY (`Curso_idCurso`)
    REFERENCES `Curso` (`idCurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Atividade` (
  `idAtividade` INT NOT NULL AUTO_INCREMENT,
  `nomeAtividade` VARCHAR(45) NOT NULL,
  `tempoEmprestimo` INT NOT NULL,
  PRIMARY KEY (`idAtividade`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Emprestimo` (
  `idEmprestimo` INT NOT NULL AUTO_INCREMENT,
  `dataSaida` DATETIME NULL,
  `dataEntrega` DATETIME NULL,
  `dataDevolucao` DATETIME NULL,
  `quantidadeEmprestimo` INT NOT NULL,
  `matricula` INT NOT NULL,
  `idAtividade` INT NOT NULL,
  `idEquipamentoEmprestado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEmprestimo`, `matricula`),
  INDEX `fk_Emprestimo_Alunos1_idx` (`matricula` ASC) VISIBLE,
  INDEX `fk_Emprestimo_Atividade1_idx` (`idAtividade` ASC) VISIBLE,
  CONSTRAINT `fk_Emprestimo_Alunos1`
    FOREIGN KEY (`matricula`)
    REFERENCES `Alunos` (`matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Emprestimo_Atividade1`
    FOREIGN KEY (`idAtividade`)
    REFERENCES `Atividade` (`idAtividade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Equipamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Equipamento` (
  `idEquipamento` INT NOT NULL AUTO_INCREMENT,
  `nomeMaterial` VARCHAR(45) NOT NULL,
  `reserva` TINYINT NULL DEFAULT 0,
  `emprestado` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`idEquipamento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kit` (
  `idKit` INT NOT NULL AUTO_INCREMENT,
  `reserva` TINYINT NOT NULL,
  `emprestado` TINYINT NULL,
  PRIMARY KEY (`idKit`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Semestre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Semestre` (
  `idSemestre` INT NOT NULL AUTO_INCREMENT,
  `primeiroDiaLetivo` DATETIME NOT NULL,
  `ultimoDiaLetivo` DATETIME NOT NULL,
  PRIMARY KEY (`idSemestre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Atividade_has_Alunos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Atividade_has_Alunos` (
  `Atividade_idAtividade` INT NOT NULL,
  `Alunos_matricula` INT NOT NULL,
  PRIMARY KEY (`Atividade_idAtividade`, `Alunos_matricula`),
  INDEX `fk_Atividade_has_Alunos_Alunos1_idx` (`Alunos_matricula` ASC) VISIBLE,
  INDEX `fk_Atividade_has_Alunos_Atividade1_idx` (`Atividade_idAtividade` ASC) VISIBLE,
  CONSTRAINT `fk_Atividade_has_Alunos_Atividade1`
    FOREIGN KEY (`Atividade_idAtividade`)
    REFERENCES `Atividade` (`idAtividade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Atividade_has_Alunos_Alunos1`
    FOREIGN KEY (`Alunos_matricula`)
    REFERENCES `Alunos` (`matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Semestre_has_Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Semestre_has_Curso` (
  `Semestre_idSemestre` INT NOT NULL,
  `Curso_idCurso` INT NOT NULL,
  PRIMARY KEY (`Semestre_idSemestre`, `Curso_idCurso`),
  INDEX `fk_Semestre_has_Curso_Curso1_idx` (`Curso_idCurso` ASC) VISIBLE,
  INDEX `fk_Semestre_has_Curso_Semestre1_idx` (`Semestre_idSemestre` ASC) VISIBLE,
  CONSTRAINT `fk_Semestre_has_Curso_Semestre1`
    FOREIGN KEY (`Semestre_idSemestre`)
    REFERENCES `Semestre` (`idSemestre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Semestre_has_Curso_Curso1`
    FOREIGN KEY (`Curso_idCurso`)
    REFERENCES `Curso` (`idCurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kit_has_Equipamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kit_has_Equipamento` (
  `Kit_idKit` INT NOT NULL,
  `Equipamento_idEquipamento` INT NOT NULL,
  PRIMARY KEY (`Kit_idKit`, `Equipamento_idEquipamento`),
  INDEX `fk_Kit_has_Equipamento_Equipamento1_idx` (`Equipamento_idEquipamento` ASC) VISIBLE,
  INDEX `fk_Kit_has_Equipamento_Kit1_idx` (`Kit_idKit` ASC) VISIBLE,
  CONSTRAINT `fk_Kit_has_Equipamento_Kit1`
    FOREIGN KEY (`Kit_idKit`)
    REFERENCES `Kit` (`idKit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Kit_has_Equipamento_Equipamento1`
    FOREIGN KEY (`Equipamento_idEquipamento`)
    REFERENCES `Equipamento` (`idEquipamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Emprestimo_has_Semestre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Emprestimo_has_Semestre` (
  `Emprestimo_idEmprestimo` INT NOT NULL,
  `Emprestimo_matricula` INT NOT NULL,
  `Semestre_idSemestre` INT NOT NULL,
  PRIMARY KEY (`Emprestimo_idEmprestimo`, `Emprestimo_matricula`, `Semestre_idSemestre`),
  INDEX `fk_Emprestimo_has_Semestre_Semestre1_idx` (`Semestre_idSemestre` ASC) VISIBLE,
  INDEX `fk_Emprestimo_has_Semestre_Emprestimo1_idx` (`Emprestimo_idEmprestimo` ASC, `Emprestimo_matricula` ASC) VISIBLE,
  CONSTRAINT `fk_Emprestimo_has_Semestre_Emprestimo1`
    FOREIGN KEY (`Emprestimo_idEmprestimo` , `Emprestimo_matricula`)
    REFERENCES `Emprestimo` (`idEmprestimo` , `matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Emprestimo_has_Semestre_Semestre1`
    FOREIGN KEY (`Semestre_idSemestre`)
    REFERENCES `Semestre` (`idSemestre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Equipamento_has_Emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Equipamento_has_Emprestimo` (
  `Equipamento_idEquipamento` INT NOT NULL,
  `Emprestimo_idEmprestimo` INT NOT NULL,
  `Emprestimo_matricula` INT NOT NULL,
  PRIMARY KEY (`Equipamento_idEquipamento`, `Emprestimo_idEmprestimo`, `Emprestimo_matricula`),
  INDEX `fk_Equipamento_has_Emprestimo_Emprestimo1_idx` (`Emprestimo_idEmprestimo` ASC, `Emprestimo_matricula` ASC) VISIBLE,
  INDEX `fk_Equipamento_has_Emprestimo_Equipamento1_idx` (`Equipamento_idEquipamento` ASC) VISIBLE,
  CONSTRAINT `fk_Equipamento_has_Emprestimo_Equipamento1`
    FOREIGN KEY (`Equipamento_idEquipamento`)
    REFERENCES `Equipamento` (`idEquipamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipamento_has_Emprestimo_Emprestimo1`
    FOREIGN KEY (`Emprestimo_idEmprestimo` , `Emprestimo_matricula`)
    REFERENCES `Emprestimo` (`idEmprestimo` , `matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kit_has_Emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kit_has_Emprestimo` (
  `Kit_idKit` INT NOT NULL,
  `Emprestimo_idEmprestimo` INT NOT NULL,
  `Emprestimo_matricula` INT NOT NULL,
  PRIMARY KEY (`Kit_idKit`, `Emprestimo_idEmprestimo`, `Emprestimo_matricula`),
  INDEX `fk_Kit_has_Emprestimo_Emprestimo1_idx` (`Emprestimo_idEmprestimo` ASC, `Emprestimo_matricula` ASC) VISIBLE,
  INDEX `fk_Kit_has_Emprestimo_Kit1_idx` (`Kit_idKit` ASC) VISIBLE,
  CONSTRAINT `fk_Kit_has_Emprestimo_Kit1`
    FOREIGN KEY (`Kit_idKit`)
    REFERENCES `Kit` (`idKit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Kit_has_Emprestimo_Emprestimo1`
    FOREIGN KEY (`Emprestimo_idEmprestimo` , `Emprestimo_matricula`)
    REFERENCES `Emprestimo` (`idEmprestimo` , `matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Data for table `Curso`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Curso` (`idCurso`, `nomeCurso`) VALUES (1020, 'Eng Telecom');
INSERT INTO `Curso` (`idCurso`, `nomeCurso`) VALUES (1050, 'Quimica');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Alunos`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Alunos` (`matricula`, `nome`, `sobrenome`, `situacao`, `Curso_idCurso`, `temEmprestimo`, `penalidade`) VALUES (123, 'Renan', 'Rodolfo', 1, 1, 1, NULL);
INSERT INTO `Alunos` (`matricula`, `nome`, `sobrenome`, `situacao`, `Curso_idCurso`, `temEmprestimo`, `penalidade`) VALUES (124, 'Rodolfo', 'Silva', 1, 1, 1, NULL);
INSERT INTO `Alunos` (`matricula`, `nome`, `sobrenome`, `situacao`, `Curso_idCurso`, `temEmprestimo`, `penalidade`) VALUES (125, 'Lara', 'Postergast', 1, 1, 1, NULL);
INSERT INTO `Alunos` (`matricula`, `nome`, `sobrenome`, `situacao`, `Curso_idCurso`, `temEmprestimo`, `penalidade`) VALUES (126, 'Naiara', 'Chagas', 1, 1, 1, NULL);
INSERT INTO `Alunos` (`matricula`, `nome`, `sobrenome`, `situacao`, `Curso_idCurso`, `temEmprestimo`, `penalidade`) VALUES (127, 'Paulo', 'Giga', 0, 1, 0, NULL);
INSERT INTO `Alunos` (`matricula`, `nome`, `sobrenome`, `situacao`, `Curso_idCurso`, `temEmprestimo`, `penalidade`) VALUES (128, 'Klebinho', 'Monster', 1, 1, 1, NULL);
INSERT INTO `Alunos` (`matricula`, `nome`, `sobrenome`, `situacao`, `Curso_idCurso`, `temEmprestimo`, `penalidade`) VALUES (129, 'Vitor', 'Seis', 1, 1, 0, NULL);
INSERT INTO `Alunos` (`matricula`, `nome`, `sobrenome`, `situacao`, `Curso_idCurso`, `temEmprestimo`, `penalidade`) VALUES (130, 'Andre', 'Pereiira da Silva', 0, 1, 0, NULL);
INSERT INTO `Alunos` (`matricula`, `nome`, `sobrenome`, `situacao`, `Curso_idCurso`, `temEmprestimo`, `penalidade`) VALUES (131, 'Prato', 'Raso', 1, 1, 0, NULL);
INSERT INTO `Alunos` (`matricula`, `nome`, `sobrenome`, `situacao`, `Curso_idCurso`, `temEmprestimo`, `penalidade`) VALUES (132, 'Raquel', 'Shciliting', 1, 1, 0, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Atividade`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Atividade` (`idAtividade`, `nomeAtividade`, `tempoEmprestimo`) VALUES (500, 'atividades de ensino', 15);
INSERT INTO `Atividade` (`idAtividade`, `nomeAtividade`, `tempoEmprestimo`) VALUES (501, 'Atividades de pesquisa', 0);
INSERT INTO `Atividade` (`idAtividade`, `nomeAtividade`, `tempoEmprestimo`) VALUES (502, 'Atividade de extensão', 0);
INSERT INTO `Atividade` (`idAtividade`, `nomeAtividade`, `tempoEmprestimo`) VALUES (503, 'TCC', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Emprestimo`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Emprestimo` (`idEmprestimo`, `dataSaida`, `dataEntrega`, `dataDevolucao`, `quantidadeEmprestimo`, `matricula`, `idAtividade`, `idEquipamentoEmprestado`) VALUES (700, '2021-04-01 15:30:00', NULL, '2021-04-16 15:30:00', 2, 123, 500, '10');
INSERT INTO `Emprestimo` (`idEmprestimo`, `dataSaida`, `dataEntrega`, `dataDevolucao`, `quantidadeEmprestimo`, `matricula`, `idAtividade`, `idEquipamentoEmprestado`) VALUES (701, '2021-05-01 15:30:00', NULL, '2021-05-16 15:30:00', 2, 124, 500, '20');
INSERT INTO `Emprestimo` (`idEmprestimo`, `dataSaida`, `dataEntrega`, `dataDevolucao`, `quantidadeEmprestimo`, `matricula`, `idAtividade`, `idEquipamentoEmprestado`) VALUES (702, '2021-06-01 15:30:00', NULL, '2021-09-28 00:00:00', 1, 125, 501, '30');
INSERT INTO `Emprestimo` (`idEmprestimo`, `dataSaida`, `dataEntrega`, `dataDevolucao`, `quantidadeEmprestimo`, `matricula`, `idAtividade`, `idEquipamentoEmprestado`) VALUES (703, '2021-07-01 15:30:00', NULL, '2021-09-28 00:00:00', 1, 126, 502, '60');
INSERT INTO `Emprestimo` (`idEmprestimo`, `dataSaida`, `dataEntrega`, `dataDevolucao`, `quantidadeEmprestimo`, `matricula`, `idAtividade`, `idEquipamentoEmprestado`) VALUES (704, '2021-04-01 15:30:00', '2021-04-15 15:30:00', '2021-04-16 15:30:00', 1, 123, 500, '70');
INSERT INTO `Emprestimo` (`idEmprestimo`, `dataSaida`, `dataEntrega`, `dataDevolucao`, `quantidadeEmprestimo`, `matricula`, `idAtividade`, `idEquipamentoEmprestado`) VALUES (705, '2021-04-10 15:30:00', '2021-04-13 15:30:00', '2021-04-25 15:30:00', 1, 124, 500, '80');
INSERT INTO `Emprestimo` (`idEmprestimo`, `dataSaida`, `dataEntrega`, `dataDevolucao`, `quantidadeEmprestimo`, `matricula`, `idAtividade`, `idEquipamentoEmprestado`) VALUES (706, '2021-04-15 15:30:00', '2021-04-20 15:30:00', '2021-04-30 15:30:00', 1, 127, 500, '1');
INSERT INTO `Emprestimo` (`idEmprestimo`, `dataSaida`, `dataEntrega`, `dataDevolucao`, `quantidadeEmprestimo`, `matricula`, `idAtividade`, `idEquipamentoEmprestado`) VALUES (707, '2021-06-02 15:30:00', '2021-08-15 15:30:00', '2021-09-28 00:00:00', 1, 129, 501, '5');
INSERT INTO `Emprestimo` (`idEmprestimo`, `dataSaida`, `dataEntrega`, `dataDevolucao`, `quantidadeEmprestimo`, `matricula`, `idAtividade`, `idEquipamentoEmprestado`) VALUES (708, '2021-07-10 14:20:00', NULL, '2021-09-28 00:00:00', 1, 128, 502, '2');
INSERT INTO `Emprestimo` (`idEmprestimo`, `dataSaida`, `dataEntrega`, `dataDevolucao`, `quantidadeEmprestimo`, `matricula`, `idAtividade`, `idEquipamentoEmprestado`) VALUES (709, '2021-05-10 14:20:00', '2021-07-15 14:20:00', '2021-07-25 14:20:00', 1, 130, 500, '8');
INSERT INTO `Emprestimo` (`idEmprestimo`, `dataSaida`, `dataEntrega`, `dataDevolucao`, `quantidadeEmprestimo`, `matricula`, `idAtividade`, `idEquipamentoEmprestado`) VALUES (710, '2021-06-10 14:20:00', '2021-07-17 14:20:00', '2021-07-25 14:20:00', 1, 131, 500, '9');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Equipamento`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Equipamento` (`idEquipamento`, `nomeMaterial`, `reserva`, `emprestado`) VALUES (1, 'Cabo USB', 0, 1);
INSERT INTO `Equipamento` (`idEquipamento`, `nomeMaterial`, `reserva`, `emprestado`) VALUES (2, 'Arduino', 0, 1);
INSERT INTO `Equipamento` (`idEquipamento`, `nomeMaterial`, `reserva`, `emprestado`) VALUES (3, 'Capacitor 1u', 0, 0);
INSERT INTO `Equipamento` (`idEquipamento`, `nomeMaterial`, `reserva`, `emprestado`) VALUES (4, 'Resistor 1K', 0, 0);
INSERT INTO `Equipamento` (`idEquipamento`, `nomeMaterial`, `reserva`, `emprestado`) VALUES (5, 'Cabo HDMI', 0, 0);
INSERT INTO `Equipamento` (`idEquipamento`, `nomeMaterial`, `reserva`, `emprestado`) VALUES (6, 'Protoboard', 0, 0);
INSERT INTO `Equipamento` (`idEquipamento`, `nomeMaterial`, `reserva`, `emprestado`) VALUES (7, 'Jumpers macho-macho', 0, 0);
INSERT INTO `Equipamento` (`idEquipamento`, `nomeMaterial`, `reserva`, `emprestado`) VALUES (8, 'Led', 0, 0);
INSERT INTO `Equipamento` (`idEquipamento`, `nomeMaterial`, `reserva`, `emprestado`) VALUES (9, 'Resistor 900 ohms', 0, 0);
INSERT INTO `Equipamento` (`idEquipamento`, `nomeMaterial`, `reserva`, `emprestado`) VALUES (11, 'Trimpot', 0, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Kit`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Kit` (`idKit`, `reserva`, `emprestado`) VALUES (10, 0, 1);
INSERT INTO `Kit` (`idKit`, `reserva`, `emprestado`) VALUES (20, 0, 1);
INSERT INTO `Kit` (`idKit`, `reserva`, `emprestado`) VALUES (30, 0, 1);
INSERT INTO `Kit` (`idKit`, `reserva`, `emprestado`) VALUES (40, 0, 0);
INSERT INTO `Kit` (`idKit`, `reserva`, `emprestado`) VALUES (50, 0, 0);
INSERT INTO `Kit` (`idKit`, `reserva`, `emprestado`) VALUES (60, 0, 1);
INSERT INTO `Kit` (`idKit`, `reserva`, `emprestado`) VALUES (70, 0, 0);
INSERT INTO `Kit` (`idKit`, `reserva`, `emprestado`) VALUES (90, 0, 0);
INSERT INTO `Kit` (`idKit`, `reserva`, `emprestado`) VALUES (100, 0, 0);
INSERT INTO `Kit` (`idKit`, `reserva`, `emprestado`) VALUES (0, 0, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Semestre`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Semestre` (`idSemestre`, `primeiroDiaLetivo`, `ultimoDiaLetivo`) VALUES (1, '2021-03-1 00:00:00', '2021-09-28 00:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Atividade_has_Alunos`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Atividade_has_Alunos` (`Atividade_idAtividade`, `Alunos_matricula`) VALUES (500, 123);
INSERT INTO `Atividade_has_Alunos` (`Atividade_idAtividade`, `Alunos_matricula`) VALUES (500, 124);
INSERT INTO `Atividade_has_Alunos` (`Atividade_idAtividade`, `Alunos_matricula`) VALUES (501, 125);
INSERT INTO `Atividade_has_Alunos` (`Atividade_idAtividade`, `Alunos_matricula`) VALUES (500, 126);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Semestre_has_Curso`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Semestre_has_Curso` (`Semestre_idSemestre`, `Curso_idCurso`) VALUES (1, 1020);
INSERT INTO `Semestre_has_Curso` (`Semestre_idSemestre`, `Curso_idCurso`) VALUES (1, 1050);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Kit_has_Equipamento`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (10, 2);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (10, 3);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (10, 6);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (20, 2);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (20, 8);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (20, 9);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (30, 1);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (30, 2);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (30, 3);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (30, 4);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (60, 1);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (60, 2);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (40, 2);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (40, 10);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (40, 9);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (50, 2);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (50, 3);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (50, 8);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (50, 5);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (50, 6);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (70, 6);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (70, 3);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (70, 4);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (70, 8);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (80, 1);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (80, 5);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (90, 6);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (90, 7);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (100, 2);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (100, 1);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (100, 10);
INSERT INTO `Kit_has_Equipamento` (`Kit_idKit`, `Equipamento_idEquipamento`) VALUES (100, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Equipamento_has_Emprestimo`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Equipamento_has_Emprestimo` (`Equipamento_idEquipamento`, `Emprestimo_idEmprestimo`, `Emprestimo_matricula`) VALUES (1, 706, 127);
INSERT INTO `Equipamento_has_Emprestimo` (`Equipamento_idEquipamento`, `Emprestimo_idEmprestimo`, `Emprestimo_matricula`) VALUES (2, 708, 129);
INSERT INTO `Equipamento_has_Emprestimo` (`Equipamento_idEquipamento`, `Emprestimo_idEmprestimo`, `Emprestimo_matricula`) VALUES (5, 707, 128);
INSERT INTO `Equipamento_has_Emprestimo` (`Equipamento_idEquipamento`, `Emprestimo_idEmprestimo`, `Emprestimo_matricula`) VALUES (8, 709, 130);
INSERT INTO `Equipamento_has_Emprestimo` (`Equipamento_idEquipamento`, `Emprestimo_idEmprestimo`, `Emprestimo_matricula`) VALUES (9, 710, 131);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Kit_has_Emprestimo`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Kit_has_Emprestimo` (`Kit_idKit`, `Emprestimo_idEmprestimo`, `Emprestimo_matricula`) VALUES (10, 700, 123);
INSERT INTO `Kit_has_Emprestimo` (`Kit_idKit`, `Emprestimo_idEmprestimo`, `Emprestimo_matricula`) VALUES (20, 701, 124);
INSERT INTO `Kit_has_Emprestimo` (`Kit_idKit`, `Emprestimo_idEmprestimo`, `Emprestimo_matricula`) VALUES (30, 702, 125);
INSERT INTO `Kit_has_Emprestimo` (`Kit_idKit`, `Emprestimo_idEmprestimo`, `Emprestimo_matricula`) VALUES (60, 703, 126);
INSERT INTO `Kit_has_Emprestimo` (`Kit_idKit`, `Emprestimo_idEmprestimo`, `Emprestimo_matricula`) VALUES (70, 704, 123);
INSERT INTO `Kit_has_Emprestimo` (`Kit_idKit`, `Emprestimo_idEmprestimo`, `Emprestimo_matricula`) VALUES (80, 705, 124);

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
