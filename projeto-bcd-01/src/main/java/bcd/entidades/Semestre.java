package bcd.entidades;

import java.sql.Timestamp;

public class Semestre {

    private int idSemestre;
    public static String primeiroDiaLetivo = "2021-03-1 15:30:00";
    public static String ultimoDiaLetivo = "2021-09-28 15:30:00";


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
