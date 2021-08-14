package bcd.entidades;

public class Alunos {
    private int matricula;
    private String nome;
    private String sobrenome;
    private boolean situacao;
    private int idCurso;

    public Alunos(int matricula, String nome, String sobrenome, boolean situacao, int idCurso) {
        this.matricula = matricula;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.situacao = situacao;
        this.idCurso = idCurso;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
}
