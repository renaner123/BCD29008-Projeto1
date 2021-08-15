package bcd.entidades;

import bcd.db.ConnectionFactory;

import java.sql.*;

public class EmprestimoDAO {

    public EmprestimoDAO(){

    }

    public String emprestimosEmAndamento() {
        StringBuilder sb = new StringBuilder();

        String query = "SELECT DISTINCT e.idEmprestimo, e.Kit_idKit,GROUP_CONCAT(idEquipamento) as idEquipamentos, dataSaida, dataDevolucao, Alunos_matricula, Atividade_idAtividade, nome from emprestimo e " +
            "INNER JOIN alunos a ON " +
            "a.matricula = e.Alunos_matricula " +
            "INNER JOIN equipamentosemprestados equiemp ON " +
            "equiemp.Kit_idKit = e.Kit_idKit " +
            "INNER JOIN equipamento eq ON equiemp.Equipamento_idEquipamento = eq.idEquipamento " +
            "WHERE e.dataEntrega is NULL AND equiemp.devolvido = 0 group by equiemp.Kit_idKit ";


        try (PreparedStatement stmt = ConnectionFactory.getDBConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                sb.append("---------------------------------------------------------------------------------------------------------------------------------------\n");
                sb.append(String.format("|  %-12s  |  %-5s  |  %-14s  |  %-21s  |  %-21s  |  %-15s  |  %-12s  |\n", "idEmprestimo", "idKit", "idEquipamentos", "DataSaida", "DataDevolucao","Matricula Aluno", "ID atividade"));
                sb.append("---------------------------------------------------------------------------------------------------------------------------------------\n");

                do {
                    int idEmprestimo = rs.getInt("idEmprestimo");
                    int idKit = rs.getInt("Kit_idKit");
                    String idEquipamantos = rs.getString("idEquipamentos");
                    Timestamp dataSaida = rs.getTimestamp("dataSaida");
                    Timestamp dataDevolucao = rs.getTimestamp("dataDevolucao");
                    int matriculaALuno = rs.getInt("Alunos_matricula");
                    int idAtividade = rs.getInt("Atividade_idAtividade");
                    String nome = rs.getString("nome");

                    sb.append(String.format("|  %-12s  |  %-5s  |  %-14s  |  %-21s  |  %-21s  |  %-17s  |  %-12s  |\n", idEmprestimo, idKit, idEquipamantos, dataSaida, dataDevolucao, matriculaALuno,idAtividade));
                } while (rs.next());
                sb.append("---------------------------------------------------------------------------------------------------------------------------------------\n");
            } else {
                sb.append("Não há registros no banco de dados\n");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String emprestimoDeterminadoMaterial(int material) {
        StringBuilder sb = new StringBuilder();

        String query = "SELECT DISTINCT idEmprestimo, nome, sobrenome, matricula, Atividade_idAtividade, e.Kit_idKit from emprestimo e "+
        "inner join equipamentosemprestados equiem ON " +
        "equiem.Kit_idKit = e.Kit_idKit " +
        "inner join alunos ON "+
        "Alunos_matricula = matricula WHERE "+
        "equiem.Equipamento_idEquipamento = ? ";


        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setInt(1,material);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                sb.append("---------------------------------------------------------------------------------------------------------------------------------------\n");
                sb.append(String.format("|  %-12s  |  %-10s  |  %-9s  |  %-15s  |  %-12s  |  %-6s  |\n", "idEmprestimo", "nome", "sobrenome", "Matricula Aluno", "ID atividade", "ID Kit"));
                sb.append("---------------------------------------------------------------------------------------------------------------------------------------\n");

                do {
                    int idEmprestimo = rs.getInt("idEmprestimo");
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    int matricula = rs.getInt("matricula");
                    int idAtividade = rs.getInt("Atividade_idAtividade");
                    int idKit = rs.getInt("Kit_idKit");

                    sb.append(String.format("|  %-12s  |  %-10s  |  %-9s  |  %-15s  |  %-12s  |  %-6s  |\n", idEmprestimo, nome, sobrenome, matricula, idAtividade, idKit));
                } while (rs.next());

                sb.append("---------------------------------------------------------------------------------------------------------------------------------------\n");
            } else {
                sb.append("Não há registros no banco de dados\n");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String emprestimoDeterminadoAluno(int matriculaFind) {

        StringBuilder sb = new StringBuilder();

        String query = "SELECT DISTINCT idEmprestimo, nome, sobrenome, matricula, Alunos_matricula, Atividade_idAtividade, e.Kit_idKit, GROUP_CONCAT(idEquipamento) AS equipamentos "+
        "from emprestimo e " +
        "inner join alunos a ON " +
        "a.matricula = ? " +
        "INNER JOIN equipamentosemprestados equiemp ON "+
        "equiemp.Kit_idKit = e.Kit_idKit " +
        "INNER JOIN equipamento eq ON " +
        "equiemp.Equipamento_idEquipamento = eq.idEquipamento " +
        "where e.Alunos_matricula = ? AND e.dataEntrega is NULL ";


        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setInt(1,matriculaFind);
            stmt.setInt(2,matriculaFind);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                sb.append("---------------------------------------------------------------------------------------------------------------\n");
                sb.append(String.format("|  %-12s  |  %-10s  |  %-9s  |  %-15s  |  %-12s  |  %-6s  | %-10s|\n", "ID Emprestimo", "Nome", "Sobrenome", "Matricula", "Atividade", "ID Kit", "idEquipamento"));
                sb.append("---------------------------------------------------------------------------------------------------------------\n");

                do {
                    int idEmprestimo = rs.getInt("idEmprestimo");
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    int matricula = rs.getInt("matricula");
                    int idAtividade = rs.getInt("Atividade_idAtividade");
                    int idKit = rs.getInt("Kit_idKit");
                    String idEquipamento = rs.getString(("equipamentos"));

                    sb.append(String.format("|  %-12s   |  %-10s  |  %-9s  |  %-15s  |  %-12s  |  %-6s  |  %-10s  |\n", idEmprestimo, nome, sobrenome, matricula, idAtividade, idKit, idEquipamento));
                } while (rs.next());

                sb.append("---------------------------------------------------------------------------------------------------------------\n");
            } else {
                sb.append("Não há registros no banco de dados\n");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String emprestimosEmAndamentoVencidos() {

        StringBuilder sb = new StringBuilder();

        String query = "SELECT * from emprestimo e " +
        "where NOW() > dataDevolucao AND dataEntrega is NULL ";


        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                sb.append("------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                sb.append(String.format("|  %-13s  |  %-21s  |  %-21s  |  %-21s  |  %-13s  |  %-15s  | %-13s|\n", "ID Emprestimo", "Data Saida", "Data Entrega", "Data Devolucao", "ID Kit", "Matricula Aluno", "ID Atividade"));
                sb.append("------------------------------------------------------------------------------------------------------------------------------------------------------\n");

                do {
                    int idEmprestimo = rs.getInt("idEmprestimo");
                    Timestamp dataSaida = rs.getTimestamp("dataSaida");
                    Timestamp dataEntrega = rs.getTimestamp("dataEntrega");
                    Timestamp dataDevolucao = rs.getTimestamp("dataDevolucao");
                    int idKit = rs.getInt("Kit_idKit");
                    int matricula = rs.getInt("Alunos_matricula");
                    int atividade = rs.getInt("Atividade_idAtividade");


                    sb.append(String.format("|  %-13s  |  %-21s  |  %-21s  |  %-21s  |  %-13s  |  %-15s  | %-13s|\n", idEmprestimo, dataSaida, dataEntrega, dataDevolucao, idKit, matricula, atividade));
                } while (rs.next());

                sb.append("------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            } else {
                sb.append("Não há registros no banco de dados\n");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String efetuarEmprestimo(){

        return "";
    }
}
