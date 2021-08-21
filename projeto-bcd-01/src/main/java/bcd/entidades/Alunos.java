package bcd.entidades;

/**
 * Classe responsável por gerar um objeto de Aluno
 */
public class Alunos {
    private int matricula;
    private String nome;
    private String sobrenome;
    private boolean situacao;
    private int idCurso;
    private boolean temEmprestimo;
    private int penalidade = 0;

    /**
     * Método construtor de Aluno
     * @param matricula recebe matricula do aluno
     * @param nome recebe nome do aluno
     * @param sobrenome recebe sobrenome do aluno
     * @param situacao informa a situacao do aluno no curso
     * @param idCurso informa id do curso cursado pelo aluno
     * @param emprestimo informa se o usuario possui emprestimo vigente
     * @param penalidade recebe penalidade em dias por entregar emprestimo fora da data de devolucao
     */
    public Alunos(int matricula, String nome, String sobrenome, boolean situacao, int idCurso, boolean emprestimo, int penalidade) {
        this.matricula = matricula;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.situacao = situacao;
        this.idCurso = idCurso;
        this.temEmprestimo = emprestimo;
        this.penalidade = penalidade;
    }


    public int getPenalidade() {
        return penalidade;
    }

    public void setPenalidade(int penalidade) {
        this.penalidade = penalidade;
    }

    public boolean isTemEmprestimo() {
        return temEmprestimo;
    }

    public void setTemEmprestimo(boolean temEmprestimo) {
        this.temEmprestimo = temEmprestimo;
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
