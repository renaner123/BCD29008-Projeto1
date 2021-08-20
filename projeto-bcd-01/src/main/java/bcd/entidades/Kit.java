package bcd.entidades;

public class Kit {

    private int idKit;
    private boolean emprestado;
    private boolean reserva;

    public Kit(int id,boolean emprestado, boolean reserva){
        this.idKit = id;
        this.emprestado = emprestado;
        this.reserva = reserva;
    }

    public boolean isReserva() {
        return reserva;
    }

    public void setReserva(boolean reserva) {
        this.reserva = reserva;
    }

    public int getIdKit() {
        return idKit;
    }

    public void setIdKit(int idKit) {
        this.idKit = idKit;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }
}
