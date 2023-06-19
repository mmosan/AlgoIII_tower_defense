package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.AlgoritmoDeBresenham;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

import java.util.ArrayList;

public class MovimientoCateto implements Movimiento{

    private ArrayList<Coordenada> camino;
    private Coordenada meta;
    private Coordenada posicionActual;

    private Coordenada vertice;
    private Parcela parcelaActual;

    private Enemigo enemigo;

    public MovimientoCateto(Enemigo enemigo){
        this.enemigo = enemigo;
    }

    @Override
    public void reasignarPosiciones(Coordenada coordInicial, Coordenada coordFinal){
        this.posicionActual = coordInicial;
        this.meta = coordFinal;
    }
    @Override
    public void actualizarPosicion(Parcela parcelaNueva){
        this.parcelaActual = parcelaNueva;
        parcelaNueva.actualizarUbicacion(this);
    }

    @Override
    public void actualizarPosicion(Parcela parcelaInicial, Parcela parcelaFinal){
        this.parcelaActual = parcelaInicial;
        parcelaInicial.actualizarUbicacion(this);
        parcelaFinal.actulizarMeta(this);

        this.vertice = new Coordenada(this.meta.getAbscisa(), this.posicionActual.getOrdenada());
        this.camino = AlgoritmoDeBresenham.getCamino(this.posicionActual, this.vertice);
    }

    @Override
    public void avanzar(int cantidadPasos, Mapa mapa){
        for(int posicion = 0; posicion < cantidadPasos; posicion++){

            if (this.posicionActual == this.vertice){
                this.camino = AlgoritmoDeBresenham.getCamino(this.vertice, this.meta);
            }

            this.posicionActual = this.camino.get(posicion);
            this.parcelaActual = mapa.ver(this.posicionActual);
            this.parcelaActual.actualizarPosicion(this);
        }

        this.camino.subList(0, cantidadPasos).clear();

    }

    @Override
    public void actualizarUbicacion(Coordenada posicionNueva){
        this.posicionActual = posicionNueva;
    }

    @Override
    public void establecerMeta(Coordenada coordenada){
        this.meta = coordenada;
    }

    @Override
    public void daniarJugador(){
        this.enemigo.daniarJugador();
    }

    public boolean estaEnRango(Coordenada coordenada, int distancia){
        return this.posicionActual.estaEnRango(coordenada, distancia);
    }

    @Override
    public String representarUbicacion(){
        return this.posicionActual.representacionString();
    }

    @Override
    public Movimiento setMovimiento(Movimiento otroMovimiento){
        otroMovimiento.reasignarPosiciones(this.posicionActual, this.meta);

        return otroMovimiento;
    }

}