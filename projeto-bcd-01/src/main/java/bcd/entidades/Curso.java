package bcd.entidades;

/**
 * Classe responsável por criar um objeto do tipo curso
 */
public class Curso {

    private int idCurso;
    private String nomeCurso;

    /**
     * Construtor de um objeto Curso
     * @param idCurso recebe id do curso
     * @param nomeCurso recebe o nome do curso
     */
    public Curso(int idCurso, String nomeCurso) {
        this.idCurso = idCurso;
        this.nomeCurso = nomeCurso;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
}
