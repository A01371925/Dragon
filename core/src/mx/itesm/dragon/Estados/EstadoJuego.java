package mx.itesm.dragon.Estados;

public class EstadoJuego {

    private Estado estado;

    public EstadoJuego() {
        this.estado = Estado.JUGANDO;
    }

    public EstadoJuego(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void estado() {
        switch (estado) {
            case JUGANDO:

                break;
            case PAUSA:
                break;
        }
    }
}
