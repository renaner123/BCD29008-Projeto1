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

                sb.append("------------------------------------------------------\n");
                sb.append(String.format("|%-5s|%-5s|%-35s|%-35s|%-5s|%-5s|%-35s|\n", "idEmprestimo", "idKit", "idEquipamentos", "DataSaida", "DataDevolucao","Matricula Aluno", "ID atividade"));
                sb.append("------------------------------------------------------\n");

                do {
                    int idEmprestimo = rs.getInt("idEmprestimo");
                    int idKit = rs.getInt("Kit_idKit");
                    String idEquipamantos = rs.getString("idEquipamentos");
                    Timestamp dataSaida = rs.getTimestamp("dataSaida");
                    Timestamp dataDevolucao = rs.getTimestamp("dataDevolucao");
                    int matriculaALuno = rs.getInt("Alunos_matricula");
                    int idAtividade = rs.getInt("Atividade_idAtividade");
                    String nome = rs.getString("nome");

                    sb.append(String.format("|%-5s|%-5s|%-35s|%-35s|%-5s|%-5s|%-35s|\n", idEmprestimo, idKit, idEquipamantos, dataSaida, dataDevolucao, matriculaALuno,idAtividade));
                } while (rs.next());
                sb.append("------------------------------------------------------\n");
            } else {
                sb.append("Não há registros no banco de dados\n");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
