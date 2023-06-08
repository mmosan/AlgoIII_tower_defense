package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.Defensas.Defensa;

public class Rocosa extends Parcela {
    public Rocosa(Coordenada coordenada){
        super(coordenada, new NoDisponible());
    }
    public void construirDefensa(Defensa defensa){}

    public boolean ocupada(){
        return false;
    }
}