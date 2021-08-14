package bcd.entidades;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Atividade {

    private int atividade;
    private String nomeAtividade;
    private int tempoEmprestimo;


    public Atividade(int atividade, String nomeAtividade, int tempoEmprestimo) {
        this.atividade = atividade;
        this.nomeAtividade = nomeAtividade;
        this.tempoEmprestimo = tempoEmprestimo;
    }

}
