package bcd.entidades;

import bcd.db.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AlunosDAO {

    Map<Integer,Alunos> mapAlunos = new HashMap<Integer,Alunos>();

    public AlunosDAO(){

    }

    public  Map<Integer,Alunos> obtemAlunosDB(){

        String query = "SELECT * FROM Alunos ";

        try (PreparedStatement stmt = ConnectionFactory.getDBConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery())  {

            if (rs.next()) {
                do {
                    int matriculaAluno = rs.getInt("matricula");
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    boolean situacao = rs.getBoolean("situacao");
                    int idCurso = rs.getInt("Curso_idCurso");
                    boolean temEmprestimo = rs.getBoolean("temEmprestimo");
                    int penalidade = rs.getInt("penalidade");

                    Alunos aluno = new Alunos(matriculaAluno,nome,sobrenome,situacao,idCurso,temEmprestimo,penalidade);
                    this.mapAlunos.put(aluno.getMatricula(),aluno);

                } while (rs.next());

            } else {

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return this.mapAlunos;
    }

    public boolean alterarTemEmprestimoAluno(int matricula, boolean temEmprestimo){

        String query = "UPDATE Alunos SET temEmprestimo = ? WHERE matricula = ? ";

        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setBoolean(1,temEmprestimo);
            stmt.setInt(2,matricula);
            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean setPenalidade(int matricula, int penalidade){

        String query = "UPDATE Alunos SET penalidade = ? WHERE matricula = ? ";

        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setInt(1,penalidade);
            stmt.setInt(2,matricula);
            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String alunosEmDb() {
        StringBuilder sb = new StringBuilder();

        String query = "SELECT * FROM Alunos";

        try (PreparedStatement stmt = ConnectionFactory.getDBConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                sb.append("---------------------------------------------------------------------------------------------------------------------\n");
                sb.append(String.format("| %-9s | %-15s | %-25s | %-8s | %-8s | %-14s | %-16s |\n", "Matricula", "Nome", "Sobrenome", "Situação", "id Curso", "Tem emprestimo", "Penalidade(dias)"));
                sb.append("---------------------------------------------------------------------------------------------------------------------\n");

                do {
                    int matricula = rs.getInt("matricula");
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    int situacao = rs.getInt("situacao");
                    int idCurso = rs.getInt("Curso_idCurso");
                    boolean temEmpretimo = rs.getBoolean("temEmprestimo");
                    int penalidade = rs.getInt("penalidade");



                    sb.append(String.format("| %-9s | %-15s | %-25s | %-8s | %-8s | %-14s | %-16s |\n", matricula, nome, sobrenome, situacao, idCurso, temEmpretimo, penalidade));
                } while (rs.next());
                sb.append("---------------------------------------------------------------------------------------------------------------------\n");
            } else {
                sb.append("Não há registros no banco de dados\n");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
