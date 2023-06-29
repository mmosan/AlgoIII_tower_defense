package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.DisponibleDefensa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

public class ControladorCompra {
    private Juego juego;
    private String defensaSeleccionada;

    private static final ControladorCompra INSTANCE = new ControladorCompra();
    private ControladorCompra() {
        super();
    }
    public void setJuego(Juego unJuego) { this.juego = unJuego;}

    public void seleccionarDefensa(String unaDefensa) { this.defensaSeleccionada = unaDefensa; }

    public void cancelarSeleccionDefensa() { this.defensaSeleccionada = null; }
    public static ControladorCompra getInstance() { return INSTANCE; }

    public void ponerDefensaEn(int coordX, int coordY) {
        Coordenada coord = new Coordenada(coordX, coordY);
        if (!esCompraValida(coord)) {
            return;
        }
        Juego.getInstance().comprarDefensa(defensaSeleccionada, coord);
        cancelarSeleccionDefensa();
        }
    private boolean esCompraValida(Coordenada coordenada){
        if (defensaSeleccionada == null) {
            return false;
        }
        Parcela parcela = Juego.getInstance().verParcelaEn(coordenada);
        if (!parcela.puedeConstruir(defensaSeleccionada)) {
            return false;
        }
        return true;
    }
}