package bcd.entidades;

import java.util.List;

public class EquipamentosEmprestados {
    private int idKit;
    private int idEquipamento;
    private boolean devolvido;
    private List<Integer> idsMateriais;

    public EquipamentosEmprestados(int idKit, List<Integer> idsMateriais) {
        this.idKit = idKit;
        this.idsMateriais = idsMateriais;
    }

    public int getIdKit() {
        return idKit;
    }

    public void setIdKit(int idKit) {
        this.idKit = idKit;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public List<Integer> getIdsMateriais() {
        return idsMateriais;
    }

    public void setIdsMateriais(List<Integer> idsMateriais) {
        this.idsMateriais = idsMateriais;
    }
}
