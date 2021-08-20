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
    private int idEquipamentoEmprestado;
    private int idAluno;
    private int idAtividade;

    public Emprestimo(int idEmprestimo, Timestamp dataSaida ,Timestamp dataEntrega, Timestamp dataDevolucao, int quantidadeEmprestimo, int idAluno, int idAtividade, int idEquipamentoEmprestado) {
        this.dataSaida = dataSaida;
        this.dataDevolucao = dataDevolucao;
        this.idEmprestimo = idEmprestimo;
        this.dataEntrega = dataEntrega;
        this.quantidadeEmprestimo = quantidadeEmprestimo;
        this.idEquipamentoEmprestado = idEquipamentoEmprestado;
        this.idAluno = idAluno;
        this.idAtividade = idAtividade;
    }

    public int getIdEquipamentoEmprestado() {
        return idEquipamentoEmprestado;
    }

    public void setIdEquipamentoEmprestado(int idEquipamentoEmprestado) {
        this.idEquipamentoEmprestado = idEquipamentoEmprestado;
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
