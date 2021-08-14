package bcd;

import bcd.db.ConnectionFactory;

import java.sql.*;

public class teste {

    public teste(){

    }

//    public String listarDadosDeTodosDepartamentos() {
//        StringBuilder sb = new StringBuilder();
//
//        String query = "SELECT * FROM emprestimo";
//
//        try (PreparedStatement stmt = ConnectionFactory.getDBConnection().prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            if (rs.next()) {
//
//                sb.append("------------------------------------------------------\n");
//                sb.append(String.format("|%-5s|%-35s|%10s|\n", "ID", "DataSaida", "DataEntrega"));
//                sb.append("------------------------------------------------------\n");
//
//                do {
//                    int idDepto = rs.getInt("idEmprestimo");
//                    Timestamp dataSaida = rs.getTimestamp("dataSaida");
//                    Timestamp dataEntrega = rs.getTimestamp("dataEntrega");
//
//                    sb.append(String.format("|%-5d|%-35s|%-35s|\n", idDepto, dataSaida, dataEntrega));
//                } while (rs.next());
//                sb.append("------------------------------------------------------\n");
//            } else {
//                sb.append("Não há registros no banco de dados\n");
//            }
//
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return sb.toString();
//    }

}
