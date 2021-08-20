package bcd.entidades;

public class Equipamento {

    private int idEquipamento;
    private String nomeMaterial;
    private boolean reserva;
    private boolean emprestado;

    public Equipamento(int idEquipamento, String nomeMaterial, boolean emprestado, boolean reserva) {
        this.idEquipamento = idEquipamento;
        this.nomeMaterial = nomeMaterial;
        this.emprestado = emprestado = false;
        this.reserva = reserva = false;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public boolean isReserva() {
        return reserva;
    }

    public void setReserva(boolean reserva) {
        this.reserva = reserva;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }
}
