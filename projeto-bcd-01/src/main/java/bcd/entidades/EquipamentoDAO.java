package bcd.entidades;

import bcd.db.ConnectionFactory;

import java.sql.*;
import java.util.*;
import java.util.Date;


public class EquipamentoDAO {

    Map<Integer,Equipamento> mapEquipamentos = new HashMap<Integer,Equipamento>();
    private Equipamento auxEquipamento;


    public EquipamentoDAO(){

    }

    public Map<Integer,Equipamento>  obtemEquipamentosBD(){

        String query = "SELECT * FROM Equipamento ";

        try (PreparedStatement stmt = ConnectionFactory.getDBConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                do {
                    int idEquipamento = rs.getInt("idEquipamento");
                    String nomeMaterial = rs.getString("nomeMaterial");
                    boolean reserva = rs.getBoolean("reserva");
                    boolean emprestado = rs.getBoolean("emprestado");

                    Equipamento equip = new Equipamento(idEquipamento,nomeMaterial,emprestado,reserva);
                    this.mapEquipamentos.put(equip.getIdEquipamento(),equip);

                } while (rs.next());

            } else {

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return this.mapEquipamentos;
    }

    public boolean inserirEquipamentoTemEmprestimo(int idEquipamento, int idEmprestimo, int matricula){

        String query = "INSERT INTO Equipamento_has_Emprestimo (Equipamento_idEquipamento, Emprestimo_idEmprestimo, Emprestimo_matricula) values(?,?,?) ";

        try (Connection conexao = ConnectionFactory.getDBConnection();
            PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setInt(1,idEquipamento);
            stmt.setInt(2,idEmprestimo);
            stmt.setInt(3,matricula);

            stmt.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean deleteEquipamentoTemEmprestimo(int idEmprestimo){

        String query = "DELETE FROM Equipamento_has_Emprestimo WHERE Emprestimo_idEmprestimo = ?";

        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setInt(1,idEmprestimo);
            stmt.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String equipamentosEmDb() {
        StringBuilder sb = new StringBuilder();

        String query = "SELECT * FROM Equipamento";

        try (PreparedStatement stmt = ConnectionFactory.getDBConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                sb.append("----------------------------------------------------------------\n");
                sb.append(String.format("| %-14s | %-20s | %-7s | %-10s |\n", "id Equipamento", "Nome material", "Reserva", "Emprestado"));
                sb.append("----------------------------------------------------------------\n");

                do {
                    int idEquipamento = rs.getInt("idEquipamento");
                    String nome = rs.getString("nomeMaterial");
                    boolean reserva = rs.getBoolean("reserva");
                    boolean emprestado = rs.getBoolean("emprestado");

                    sb.append(String.format("| %-14s | %-20s | %-7s | %-10s |\n", idEquipamento,nome,reserva,emprestado));
                } while (rs.next());
                sb.append("----------------------------------------------------------------\n");
            } else {
                sb.append("Não há registros no banco de dados\n");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
