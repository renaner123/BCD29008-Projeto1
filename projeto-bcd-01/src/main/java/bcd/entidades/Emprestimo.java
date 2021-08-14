package bcd.entidades;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Emprestimo {

    private int idEmprestimo;
    private Timestamp dataSaida;
    private Timestamp dataEntrega;
    private Timestamp dataDevolucao;
    private int quantidadeEmprestimo;
    private int idKit;
    private int idAluno;
    private int idAtividade;

    public Emprestimo(int idEmprestimo, Timestamp dataSaida, Timestamp dataEntrega, Timestamp dataDevolucao, int quantidadeEmprestimo, int idKit, int idAluno, int idAtividade) {
        this.idEmprestimo = idEmprestimo;
        this.dataSaida = dataSaida;
        this.dataEntrega = dataEntrega;
        this.dataDevolucao = dataDevolucao;
        this.quantidadeEmprestimo = quantidadeEmprestimo;
        this.idKit = idKit;
        this.idAluno = idAluno;
        this.idAtividade = idAtividade;
    }

    private java.sql.Timestamp obtemDataDevolucao(int atividade) throws ParseException {

        if (atividade == 500) {
            Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, 15);
            return new java.sql.Timestamp(c.getTime().getTime());
        }else{
            return java.sql.Timestamp.valueOf(Semestre.ultimoDiaLetivo);
        }
    }


    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Timestamp getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Timestamp dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Timestamp getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Timestamp dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Timestamp getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Timestamp dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getQuantidadeEmprestimo() {
        return quantidadeEmprestimo;
    }

    public void setQuantidadeEmprestimo(int quantidadeEmprestimo) {
        this.quantidadeEmprestimo = quantidadeEmprestimo;
    }

    public int getIdKit() {
        return idKit;
    }

    public void setIdKit(int idKit) {
        this.idKit = idKit;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(int idAtividade) {
        this.idAtividade = idAtividade;
    }



}
