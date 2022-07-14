package bcd.entidades;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe responsável por criar um objeto de Atividade
 */
public class Atividade {

    private int atividade;
    private String nomeAtividade;
    private int tempoEmprestimo;

    /**
     * Construtor de um objeto Atividade
     * @param atividade recebe o identificador da atividade
     * @param nomeAtividade recebe o nome da atividade
     * @param tempoEmprestimo recebe tempo de empestimo que a ativdade possui
     */
    public Atividade(int atividade, String nomeAtividade, int tempoEmprestimo) {
        this.atividade = atividade;
        this.nomeAtividade = nomeAtividade;
        this.tempoEmprestimo = tempoEmprestimo;
    }

}
