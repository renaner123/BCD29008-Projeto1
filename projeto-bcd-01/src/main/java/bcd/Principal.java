package bcd;

import bcd.entidades.*;
import java.text.ParseException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws ParseException {
        boolean sair = false;
        int opcao = 0;
        int auxFind = 0;
        EmprestimoDAO emprestimos = new EmprestimoDAO();
        KitDAO kit = new KitDAO();
        EquipamentoDAO equipamento = new EquipamentoDAO();
        AlunosDAO aluno = new AlunosDAO();
        Scanner scanner = new Scanner(System.in);
        int matricula =0;
        int atividade = 0;
        int idEquipamento = 0;
        int idEmprestimo =0 ;

        while (!sair) {
            System.out.println(" ### Selecione uma das Opções");
            System.out.println("\n\t## EMPRÉSTIMOS");
            System.out.println("\t\t1 - Efetuar empréstimo");
            System.out.println("\t\t2 - Renovar empréstimo");
            System.out.println("\t\t3 - Finalizar empréstimo");
            System.out.println("\t\t4 - Relatórios");
            System.out.println("Digite uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\t\t1 - Digite a matricula do aluno");
                    matricula = scanner.nextInt();
                    System.out.println("\t\t1 - Digite id da atividade");
                    atividade = scanner.nextInt();
                    System.out.println("\t\t1 - Digite o id do Kit ou Equipamento a ser emprestado");
                    idEquipamento = scanner.nextInt();
                    emprestimos.efetuarEmprestimo(matricula,idEquipamento,atividade);
                    break;
                case 2:
                    System.out.println("\t\t1 - Digite o id do Emprestimo");
                    idEmprestimo = scanner.nextInt();
                    System.out.println("\t\t1 - Digite a matricula do aluno");
                    matricula = scanner.nextInt();
                    emprestimos.renovarEmprestimo(idEmprestimo,matricula);
                    break;
                case 3:
                    System.out.println("\t\t1 -  Digite o id do Emprestimo");
                    idEmprestimo = scanner.nextInt();
                    emprestimos.finalizarEmprestimo(idEmprestimo);
                    break;
                case 4:

                    System.out.println("\t\t1 - Empréstimos em andamento");
                    System.out.println("\t\t2 - Empréstimos por equipamento");
                    System.out.println("\t\t3 - Equipamentos emprestados por determinado aluno");
                    System.out.println("\t\t4 - Empréstimos em andamento já vencidos");
                    System.out.println("\t\t5 - Equipamentos cadatrados");
                    System.out.println("\t\t6 - Kits cadatrados");
                    System.out.println("\t\t7 - Alunos cadastrados");
                    System.out.println("Digite uma opção: ");
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 1:
                            System.out.println(emprestimos.emprestimosEmAndamento());
                            break;
                        case 2:
                            System.out.println("Digite a ID do material");
                            auxFind = scanner.nextInt();

                            System.out.println(emprestimos.emprestimoDeterminadoMaterial(auxFind));
                            break;
                        case 3:
                            System.out.println("Digite matricula do aluno");
                            auxFind = scanner.nextInt();
                            System.out.println(emprestimos.emprestimoDeterminadoAluno(auxFind));
                            break;
                        case 4:
                            System.out.println(emprestimos.emprestimosEmAndamentoVencidos());
                            break;
                        case 5:
                            System.out.println(equipamento.equipamentosEmDb());
                            break;
                        case 6:
                            System.out.println(kit.kitEmDb());
                            break;
                        case 7:
                            System.out.println(aluno.alunosEmDb());
                            break;
                    }
            }
        }

    }

}


