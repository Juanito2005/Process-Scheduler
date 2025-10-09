package dev.juanito;

import java.util.List;

public interface AlgoritmoPlanificacion {
    ResultadoPlanificacion planificar(List<Proceso> procesosAPlanificar, Integer roundrobin);
}
