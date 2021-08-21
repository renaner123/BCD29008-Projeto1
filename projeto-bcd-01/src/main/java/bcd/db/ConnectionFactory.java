package bcd.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Faça os ajustes de configuração de conexão nas variáveis dentro do Construtor
 * dessa classe.
 * <p>
 * Verifique se sua instalação MySQL permite receber conexões por meio de sockets TCP
 */
public abstract class ConnectionFactory {

    private static final String DB_PROPERTIES_FILE = "database.properties";

    private static Connection cnx;

    /**
     * Para carregar o arquivo properties
     * @return
     */
    private static InputStream getInputStream(){

        // Para pegar o arquivo quando estiver dentro um .jar
        InputStream is = ConnectionFactory.class.getResourceAsStream("/resources/"+DB_PROPERTIES_FILE);

        // Para pegar o arquivo quando estiver executando pela IDE
        if (is == null){
            is = ConnectionFactory.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE);
        }
        return is;
    }

    /**
     * Faz a conexão em um banco de dados MySQL e retorna o objeto Connection
     *
     * @return conexão com um banco MySQL
     */
    public static synchronized Connection getDBConnection() {
        Properties properties = new Properties();

        try {
            // carregando as propriedades user, password e useSSL do arquivo database.properties
            properties.load(getInputStream());

            //Pra gerar o arquivo .jar pelo InteliJ tive que deixar os dados de conexao aqui.
            //String host = "ampto.sj.ifsc.edu.br";
            //String port = "33006";
            //String dbname = "pp01renan";
            //String user = "renan";
            //String password = "bcd1234";

            // obtendo valores para host, port e dbname do arquivo properties
            String host = properties.getProperty("host");
            String port = properties.getProperty("port");
            String dbname = properties.getProperty("database");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname;

            cnx = DriverManager.getConnection(url, properties);

        } catch (SQLException | IOException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, "erro com instrução SQL", ex);
        }

        return cnx;
    }


}