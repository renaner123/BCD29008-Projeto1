package bcd.entidades;

import java.sql.Timestamp;

/**
 * Classe responsabel por gerar um objeto de Semestre que contem data inicio e fim do semestre
 */
public class Semestre {

    private int idSemestre;
    public static String primeiroDiaLetivo = "2021-03-1 15:30:00";
    public static String ultimoDiaLetivo = "2021-09-28 15:30:00";

    /**
     * Construtor de semestre
     * @param idSemestre recebe id do semestre
     */
    public Semestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }

    public int getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }
}
