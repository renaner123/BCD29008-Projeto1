package bcd.entidades;

import bcd.db.ConnectionFactory;

import java.text.ParseException;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static java.lang.Math.abs;

/**
 * Classe que faz a comunicação com o banco de dados para gerar os emprestimos.
 */
public class EmprestimoDAO {

    Map<Integer,Emprestimo> mapEmprestimos = new HashMap<Integer,Emprestimo>();

    private AlunosDAO consultaAluno = new AlunosDAO();
    public static int RENOVACOES = 3;
    private EquipamentoDAO consultaEquipamento = new EquipamentoDAO();
    private KitDAO consultaKit = new KitDAO();
    private Alunos auxAluno;
    private Emprestimo auxEmprestimo;
    private boolean temKit;
    private boolean temEquip;
    private int idEmprestimo = 0;

    /**
     * Construtor vazio para ser possivel ser instanciado
     */
    public EmprestimoDAO(){

    }

    /**
     *
     * @return uma string com os emprestimos vigentes
     */
    public String emprestimosEmAndamento() {
        StringBuilder sb = new StringBuilder();

        String query = "SELECT DISTINCT idEmprestimo,dataSaida,dataDevolucao,idAtividade,idEquipamentoEmprestado,matricula, nome, sobrenome from Emprestimo " +
        "NATURAL JOIN Alunos " +
        "WHERE dataEntrega is NULL ";


        try (PreparedStatement stmt = ConnectionFactory.getDBConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
                sb.append(String.format("| %-13s | %-21s | %-21s | %-13s | %-14s | %-12s | %-12s | %-12s | \n", "ID Emprestimo","Data Saida","Data Devolucao","ID Atividade","id Equipamento","Matricula", "Nome", "Sobrenome"));
                sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");

                do {
                    int idEmprestimo = rs.getInt("idEmprestimo");
                    Timestamp dataSaida = rs.getTimestamp("dataSaida");
                    Timestamp dataDevolucao = rs.getTimestamp("dataDevolucao");
                    int idAtividade = rs.getInt("idAtividade");
                    int idEquipamentoEmprestado = rs.getInt("idEquipamentoEmprestado");
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    int matricula = rs.getInt("matricula");



                    sb.append(String.format("| %-13s | %-21s | %-21s | %-13s | %-14s | %-12s | %-12s | %-12s |\n", idEmprestimo,dataSaida,dataDevolucao,idAtividade,idEquipamentoEmprestado,matricula, nome, sobrenome));
                } while (rs.next());
                sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
            } else {
                sb.append("Não há registros no banco de dados\n");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     *
     * @param material recebe material que se deseja buscar nos emprestimos
     * @return uma stirng contendo as informações do emprestimo referente ao material
     */
    public String emprestimoDeterminadoMaterial(int material) {
        StringBuilder sb = new StringBuilder();

        String query = "SELECT DISTINCT idEmprestimo,dataSaida,dataDevolucao,idAtividade,idEquipamentoEmprestado,nome, sobrenome, matricula from Emprestimo " +
        "NATURAL JOIN Alunos " +
        "WHERE idEquipamentoEmprestado = ? ";


        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setInt(1,material);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
                sb.append(String.format("| %-13s | %-21s | %-21s | %-13s | %-14s | %-12s | %-12s | %-12s | \n", "ID Emprestimo","Data Saida","Data Devolucao","ID Atividade","id Equipamento","Matricula", "Nome", "Sobrenome"));
                sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");

                do {
                    int idEmprestimo = rs.getInt("idEmprestimo");
                    Timestamp dataSaida = rs.getTimestamp("dataSaida");
                    Timestamp dataDevolucao = rs.getTimestamp("dataDevolucao");
                    int idAtividade = rs.getInt("idAtividade");
                    int idEquipamentoEmprestado = rs.getInt("idEquipamentoEmprestado");
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    int matricula = rs.getInt("matricula");

                    sb.append(String.format("| %-13s | %-21s | %-21s | %-13s | %-14s | %-12s | %-12s | %-12s |\n", idEmprestimo,dataSaida,dataDevolucao,idAtividade,idEquipamentoEmprestado,matricula, nome, sobrenome));
                } while (rs.next());

                sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
            } else {
                sb.append("Não há registros no banco de dados\n");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     *
     * @param matriculaFind recebe matricula que se deseja buscar emprestimos
     * @return uma string com as informacoes do emprestimo do aluno com matriculaFind
     */
    public String emprestimoDeterminadoAluno(int matriculaFind) {

        StringBuilder sb = new StringBuilder();

        String query = "SELECT DISTINCT idEmprestimo,dataSaida,dataDevolucao,idAtividade,idEquipamentoEmprestado,nome, sobrenome, matricula from Emprestimo " +
        "NATURAL JOIN Alunos " +
        "WHERE matricula = ? AND dataEntrega is NULL ";


        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setInt(1,matriculaFind);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
                sb.append(String.format("| %-13s | %-21s | %-21s | %-13s | %-14s | %-12s | %-12s | %-12s | \n", "ID Emprestimo","Data Saida","Data Devolucao","ID Atividade","id Equipamento","Matricula", "Nome", "Sobrenome"));
                sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");

                do {
                    int idEmprestimo = rs.getInt("idEmprestimo");
                    Timestamp dataSaida = rs.getTimestamp("dataSaida");
                    Timestamp dataDevolucao = rs.getTimestamp("dataDevolucao");
                    int idAtividade = rs.getInt("idAtividade");
                    int idEquipamentoEmprestado = rs.getInt("idEquipamentoEmprestado");
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    int matricula = rs.getInt("matricula");

                    sb.append(String.format("| %-13s | %-21s | %-21s | %-13s | %-14s | %-12s | %-12s | %-12s |\n", idEmprestimo,dataSaida,dataDevolucao,idAtividade,idEquipamentoEmprestado,matricula, nome, sobrenome));
                } while (rs.next());

                sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
            } else {
                sb.append("Não há registros no banco de dados\n");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     *
     * @return uma string com as informacoes dos emprestimos que ja estao vencidos
     */
    public String emprestimosEmAndamentoVencidos() {

        StringBuilder sb = new StringBuilder();

        String query = "SELECT DISTINCT idEmprestimo,dataSaida,dataDevolucao,idAtividade,idEquipamentoEmprestado,nome, sobrenome, matricula from Emprestimo "+
        "NATURAL JOIN Alunos " +
        "WHERE dataEntrega is NULL and dataDevolucao < now() ";


        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
                sb.append(String.format("| %-13s | %-21s | %-21s | %-13s | %-14s | %-12s | %-12s | %-12s | \n", "ID Emprestimo","Data Saida","Data Devolucao","ID Atividade","id Equipamento","Matricula", "Nome", "Sobrenome"));
                sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");

                do {
                    int idEmprestimo = rs.getInt("idEmprestimo");
                    Timestamp dataSaida = rs.getTimestamp("dataSaida");
                    Timestamp dataDevolucao = rs.getTimestamp("dataDevolucao");
                    int idAtividade = rs.getInt("idAtividade");
                    int idEquipamentoEmprestado = rs.getInt("idEquipamentoEmprestado");
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    int matricula = rs.getInt("matricula");


                    sb.append(String.format("| %-13s | %-21s | %-21s | %-13s | %-14s | %-12s | %-12s | %-12s |\n", idEmprestimo,dataSaida,dataDevolucao,idAtividade,idEquipamentoEmprestado,matricula, nome, sobrenome));
                } while (rs.next());

                sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
            } else {
                sb.append("Não há registros no banco de dados\n");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     *
     * @return retorna uma hash contendo todos os emprestimos da tebela Emprestimos do banco de dados
     */
    public Map<Integer,Emprestimo>  obtemEmprestimosDB() {

        String query = "SELECT * FROM Emprestimo ";

        try (PreparedStatement stmt = ConnectionFactory.getDBConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                do {
                    int idEmprestimo = rs.getInt("idEmprestimo");
                    Timestamp dataSaida = rs.getTimestamp("dataSaida");
                    Timestamp dataEntrega = rs.getTimestamp("dataEntrega");
                    Timestamp dataDevolucao = rs.getTimestamp("dataDevolucao");
                    int quantidadeEmprestimo = rs.getInt("quantidadeEmprestimo");
                    int matricula = rs.getInt("matricula");
                    int idAtividade = rs.getInt("idAtividade");
                    int idEquipamentoEmprestado = rs.getInt("idEquipamentoEmprestado");

                    Emprestimo emprestimo = new Emprestimo(idEmprestimo,dataSaida,dataEntrega,dataDevolucao,quantidadeEmprestimo,matricula,idAtividade,idEquipamentoEmprestado);
                    this.mapEmprestimos.put(emprestimo.getIdEmprestimo(),emprestimo);


                } while (rs.next());

            } else {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.mapEmprestimos;
    }

    /**
     *
     * @param matricula recebe matricula do aluno que vai efetuar o emprestimo
     * @param id recebe id do equipamento que o aluno deseja emprestar
     * @param idAtividade recebe id da atividade a qual o emprestimo será usado
     * @return true caso consegui inserir, false caso o aluno já tenha emprestimo ou não esteja ativo no curso ou tem penalidade ou gere erro no banco de dados
     * @throws ParseException caso não consiga formatar a data.
     */
    public boolean efetuarEmprestimo(int matricula, int id, int idAtividade) throws ParseException {
        if(this.checkMatriculaDb(matricula) && this.verificaPenalidadeAluno(matricula)){
            this.auxAluno = this.consultaAluno.obtemAlunosDB().get(matricula);
            if((this.auxAluno.isTemEmprestimo()==false) && this.checkIdDb(id) && this.auxAluno.isSituacao()){
                this.consultaAluno.alterarTemEmprestimoAluno(matricula,true);
                return this.inserirEmprestimo(matricula,idAtividade,id);
            }else{
                System.out.println("Aluno já tem emprestimo vigente ou não está ativo no curso\n");
            }
        }else{
            System.out.println("Matricula não existe ou possui penalidade\n");
        }
        return false;
    }

    /**
     *
     * @param idEmprestimo recebe id do emprestimo que deseja renovar
     * @param matricula recebe matricula do aluno que fez o emprestimo
     * @return true caso consiga renovar, false caso o aluno não seja ativo ou já fez 3 renovacoes ou ja passou da data de devolucao ou gere erro no banco de dados
     * @throws ParseException caso não consiga formatar a data.
     */
    public boolean renovarEmprestimo(int idEmprestimo, int matricula) throws ParseException {

        java.util.Date date = new Date();
        java.sql.Timestamp dataAgora = new java.sql.Timestamp(date.getTime());

        if(this.obtemEmprestimosDB().containsKey(idEmprestimo)){
            this.auxEmprestimo = this.obtemEmprestimosDB().get(idEmprestimo);
            if(auxEmprestimo.getIdAluno() == matricula){
                this.auxAluno = this.consultaAluno.obtemAlunosDB().get(matricula);
                if(this.auxAluno.isSituacao() && this.auxEmprestimo.getQuantidadeEmprestimo()<RENOVACOES && this.compararDatas(dataAgora,this.auxEmprestimo.getDataDevolucao(),this.auxEmprestimo.getIdAtividade())){
                    java.sql.Timestamp novadataDevolucao = this.obtemDataDevolucao(this.auxEmprestimo.getIdAtividade());
                    this.auxEmprestimo.setDataDevolucao(novadataDevolucao);
                    this.auxEmprestimo.setQuantidadeEmprestimo(this.auxEmprestimo.getQuantidadeEmprestimo()+1);
                    this.updateRenovacaoEmprestimo(this.auxEmprestimo);
                    System.out.println("Emprestimo renovado\n");
                    return true;
                }else{
                    System.out.println("Aluno não é ativo ou já fez 3 emprestimos ou não devolveu na data\n");
                }
            }
        }else{
            System.out.println("Emprestimo não encontrado\n");
        }
        return false;
    }

    /**
     *
     * @param idEmprestimo recebe o id do emprestimo que se deseja finalizar
     * @return true caso consiga finalizar, false caso o aluno não tenha emprestimo ou gere erro no banco de dados
     */
    public boolean finalizarEmprestimo(int idEmprestimo){

        if(this.obtemEmprestimosDB().containsKey(idEmprestimo)){
            this.auxEmprestimo = this.obtemEmprestimosDB().get(idEmprestimo);
            this.auxAluno = this.consultaAluno.obtemAlunosDB().get(this.auxEmprestimo.getIdAluno());
            if(this.auxAluno.isTemEmprestimo()==false){
                System.out.println("Aluno não tem emprestimo pra finalizar\n");
                return false;
            }else{
                this.checkIdDb(this.auxEmprestimo.getIdEquipamentoEmprestado());

                java.util.Date date = new Date();
                java.sql.Timestamp dataAgora = new java.sql.Timestamp(date.getTime());

                String query = "UPDATE Emprestimo SET dataEntrega = ? WHERE idEmprestimo = ? ";

                try (Connection conexao = ConnectionFactory.getDBConnection();
                     PreparedStatement stmt = conexao.prepareStatement(query)) {

                    stmt.setTimestamp(1,dataAgora);
                    stmt.setInt(2,idEmprestimo);
                    stmt.executeUpdate();

                    this.consultaAluno.alterarTemEmprestimoAluno(this.auxEmprestimo.getIdAluno(), false);

                }catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
                if(dataAgora.after(this.auxEmprestimo.getDataDevolucao())){
                    this.consultaAluno.setPenalidade(this.auxEmprestimo.getIdAluno(),this.getDateDiff(this.auxEmprestimo.getDataDevolucao(), dataAgora, TimeUnit.HOURS)*3);
                }

                if(this.temEquip){
                    this.consultaEquipamento.deleteEquipamentoTemEmprestimo(this.auxEmprestimo.getIdEmprestimo());
                }else if(this.temKit){
                    this.consultaKit.deleteKitTemEmprestimo(this.auxEmprestimo.getIdEmprestimo());
                }
                System.out.println("Emprestimo finalizado\n");
                return true;
            }

        }else{
            System.out.println("Emprestimo não encontrado\n");
        }
        System.out.println("Não foi possível efetuar o emprestimo\n");
        return false;
    }

    /**
     *
     * @param matricula recebe matricula para verificar se existe no banco de dados
     * @return true caso exista, false caso não exista.
     */
    private boolean checkMatriculaDb(int matricula){

        if(this.consultaAluno.obtemAlunosDB().containsKey(matricula)){
            return true;
        }else
            return false;
    }

    /**
     *
     * @param id recebe id a ser checado no banco de dados
     * @return true caso o equipamento exista, false caso não exista
     */
    private boolean checkIdDb(int id){

        if(this.consultaEquipamento.obtemEquipamentosBD().containsKey(id)) {
            this.temEquip = true;
            return true;
        }
        else if(this.consultaKit.obtemEquipamentos().containsKey(id)){
            this.temKit = true;
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     * @param matricula recebe matricula do aluno que vai efetuar o emprestimo
     * @param idEmprestado recebe id do equipamento que o aluno deseja emprestar
     * @param idAtividade recebe id da atividade a qual o emprestimo será usado
     * @return true caso consegui inserir, false caso o aluno já tenha emprestimo ou não esteja ativo no curso ou tem penalidade ou gere erro no banco de dados
     * @throws ParseException caso não consiga formatar a data.
     */
    public boolean inserirEmprestimo(int matricula, int idAtividade, int idEmprestado) throws ParseException {
        StringBuilder sb = new StringBuilder();

        java.util.Date date = new Date();
        java.sql.Timestamp dataAgora = new java.sql.Timestamp(date.getTime());
        java.sql.Timestamp dataDevolucao = this.obtemDataDevolucao(idAtividade);

        String query = "INSERT INTO Emprestimo (dataSaida,dataDevolucao,quantidadeEmprestimo,matricula,idAtividade,idEquipamentoEmprestado) VALUES(?,?,?,?,?,?) ";


        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setTimestamp(1,dataAgora);
            stmt.setTimestamp(2,dataDevolucao);
            stmt.setInt(3,1);
            stmt.setInt(4,matricula);
            stmt.setInt(5,idAtividade);
            stmt.setInt(6,idEmprestado);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                this.idEmprestimo = rs.getInt(1);
            }

            if(this.temEquip){
                this.consultaEquipamento.inserirEquipamentoTemEmprestimo(idEmprestado,this.idEmprestimo,matricula);
            }else if(this.temKit){
                this.consultaKit.inserirKitTemEmprestimo(idEmprestado,this.idEmprestimo,matricula);
            }

            this.temKit =false;
            this.temEquip=false;
            System.out.println("Emprestimo realizado");

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *
     * @param emprestimo recebe o emprestido que deseja renovar
     * @return true caso consiga renovar, false caso existe erro na conexao com o banco
     */
    public boolean updateRenovacaoEmprestimo(Emprestimo emprestimo){

        String query = "UPDATE Emprestimo SET dataDevolucao = ?, quantidadeEmprestimo = ? WHERE idEmprestimo = ? ";

        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setTimestamp(1,emprestimo.getDataDevolucao());
            stmt.setInt(2,emprestimo.getQuantidadeEmprestimo());
            stmt.setInt(3,emprestimo.getIdEmprestimo());
            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     *
     * @param data1 recebe uma data Timestamp
     * @param data2 recebe uma data Timestamp
     * @return retorna se data1 está antes de data2. No caso, pra ver se está atrasado ou nao
     */
    private boolean compararDatas(Timestamp data1, Timestamp data2, int idAtividade){
        if(data1.before(data2)){
            return true;
        }else{
            if(idAtividade==501 || idAtividade == 502 || idAtividade == 503){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param atividade recebe atividade para verificar a data de devolucao do emprestimo
     * @return a data com 15 dias para atividade 500 ou data de fim do semestre
     * @throws ParseException caso haja erro na hora de formatar a data
     */
    private java.sql.Timestamp obtemDataDevolucao(int atividade) throws ParseException {
        if (atividade == 500) {
            Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, 15);
            return new Timestamp(c.getTime().getTime());
        }else{
            return Timestamp.valueOf(Semestre.ultimoDiaLetivo);
        }
    }
    /**
     *
     * @param oldTs recebe a data mais antiga
     * @param newTs recebe a data mais atual
     * @param timeUnit informa a unidade que se deseja comparar
     * @return retorna quantidade de dias de deifença entre as datas
     */
    private int getDateDiff(Timestamp oldTs, Timestamp newTs, TimeUnit timeUnit) {
        long diffInMS = newTs.getTime() - oldTs.getTime();
        int result = Math.toIntExact(timeUnit.convert(diffInMS, TimeUnit.MILLISECONDS)/24);
        return Math.abs(result);
    }

    /**
     *
     * @param matricula recebe matricula que se deseja verificar a penalidade
     * @return false caso existe penalidade ou não encontre no banco. True se nao tiver penalidade
     */
    private boolean verificaPenalidadeAluno(int matricula){
        if(this.consultaAluno.obtemAlunosDB().containsKey(matricula)){
            this.auxAluno = this.consultaAluno.obtemAlunosDB().get(matricula);
            if(this.auxAluno.getPenalidade()>0){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }

    }

}
