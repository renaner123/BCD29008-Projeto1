package bcd.entidades;

import bcd.db.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Classe responsavel por comunicar com a tabela Kit do banco de dados
 */

public class KitDAO {

    Map<Integer,Kit> mapKit = new HashMap<Integer,Kit>();

    public KitDAO(){

    }

    /**
     *
     * @return uma hash contendo os Equipamentos da tebla Equipamento do bando de dados
     */
    public Map<Integer,Kit> obtemEquipamentos() {

        String query = "SELECT * FROM Kit ";

        try (PreparedStatement stmt = ConnectionFactory.getDBConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                do {
                    int idKit = rs.getInt("idKit");
                    boolean reserva = rs.getBoolean("reserva");
                    boolean emprestado = rs.getBoolean("emprestado");

                    Kit kit = new Kit(idKit, emprestado, reserva);
                    this.mapKit.put(kit.getIdKit(), kit);
                    

                } while (rs.next());

            } else {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.mapKit;


    }

    /**
     *
     * @param idKit recebe id do kit
     * @param idEmprestimo recebe o id que foi gerado ao emprestimo
     * @param matricula recebe matricula do aluno que fez o emprestimo
     * @return true caso insira, false caso nao insira ou gere erro
     */
    public boolean inserirKitTemEmprestimo(int idKit, int idEmprestimo, int matricula){

        String query = "INSERT INTO Kit_has_Emprestimo (Kit_idKit, Emprestimo_idEmprestimo, Emprestimo_matricula) values(?,?,?) ";

        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setInt(1,idKit);
            stmt.setInt(2,idEmprestimo);
            stmt.setInt(3,matricula);

            stmt.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *
     * @param idEmprestimo recebe id do emprestimo que se deseja remover do Kit_has_Emprestimo
     * @return
     */
    public boolean deleteKitTemEmprestimo(int idEmprestimo){

        String query = "DELETE FROM Kit_has_Emprestimo WHERE Emprestimo_idEmprestimo = ?";

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

    /**
     *
     * @return string de todos os kit no banco de dados
     */
    public String kitEmDb() {
        StringBuilder sb = new StringBuilder();

        String query = "SELECT * FROM Kit";

        try (PreparedStatement stmt = ConnectionFactory.getDBConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                sb.append("---------------------------------\n");
                sb.append(String.format("| %-6s | %-7s | %-10s |\n", "id kit", "Reserva", "Emprestado"));
                sb.append("---------------------------------\n");

                do {
                    int idKit = rs.getInt("idKit");
                    boolean reserva = rs.getBoolean("reserva");
                    boolean emprestado = rs.getBoolean("emprestado");


                    sb.append(String.format("| %-6s | %-7s | %-10s |\n", idKit,reserva,emprestado));
                } while (rs.next());
                sb.append("---------------------------------\n");
            } else {
                sb.append("Não há registros no banco de dados\n");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}