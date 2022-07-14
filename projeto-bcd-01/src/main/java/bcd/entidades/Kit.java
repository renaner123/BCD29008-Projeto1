package bcd.entidades;

/**
 * Classe responsável  por gerar um objeto do tipo Kit
 */
public class Kit {

    private int idKit;
    private boolean emprestado;
    private boolean reserva;

    /**
     *
     * @param id recebe o id do kit
     * @param emprestado informa se o kid esta emprestado ou nao
     * @param reserva informa se o kit tem reserva ou nao
     */
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
