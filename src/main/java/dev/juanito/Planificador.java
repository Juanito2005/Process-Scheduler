package dev.juanito;

import java.util.List;

public class Planificador {

    private AlgoritmoPlanificacion algoritmoPlanificacion;

    public Planificador(AlgoritmoPlanificacion algoritmoPlanificacion) {
        this.algoritmoPlanificacion = algoritmoPlanificacion;
    }

    public ResultadoPlanificacion executeStrategy(List<Proceso> procesosAPlanificar, Integer roundrobin) {
        return algoritmoPlanificacion.planificar(procesosAPlanificar, roundrobin);
    }

}

