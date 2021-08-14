package bcd.entidades;

public class Equipamento {

    private int idEquipamento;
    private String nomeMaterial;
    private int vezesEmprestado;
    private boolean reserva;
    private boolean emprestado;

    public Equipamento(int idEquipamento, String nomeMaterial) {
        this.idEquipamento = idEquipamento;
        this.nomeMaterial = nomeMaterial;
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

    public int getVezesEmprestado() {
        return vezesEmprestado;
    }

    public void setVezesEmprestado(int vezesEmprestado) {
        this.vezesEmprestado = vezesEmprestado;
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
