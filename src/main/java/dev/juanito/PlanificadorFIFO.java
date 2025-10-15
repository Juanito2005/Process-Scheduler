package dev.juanito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlanificadorFIFO implements AlgoritmoPlanificacion {

    @Override
    public ResultadoPlanificacion planificar(List<Proceso> procesosAPlanificar, Integer roundrobin) {
        // La idea es que la lista enviada por el usuario no se cambie, de modo que se pueda reutilizar para ser enviada a otros algortimos
        List<Proceso> deepCopy = new ArrayList<>();
        for (Proceso proceso : procesosAPlanificar) {
            deepCopy.add(new Proceso(proceso));
        }

        // El .sort..(comparingInt) ordena de menor a mayor por defecto
        deepCopy.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));
        
        // tiempoActual representa el tiempo en que la cpu queda desocupada
        Integer tiempoActual = 0;
        List<Ejecucion> auditoriaList = new ArrayList<Ejecucion>();
        
        // Este ciclo for representa la "ejecucion" de los procesos y su auditoria
        for (Proceso p : deepCopy) {
            Integer inicioEjecucion = Math.max(p.getTiempoLlegada(), tiempoActual);
            Integer finEjecucion = inicioEjecucion + p.getDuracionCPU();
            Ejecucion auditoria = new Ejecucion(p.getId(), inicioEjecucion, finEjecucion);
            auditoriaList.add(auditoria);

            p.setTiempoInicioAb(inicioEjecucion);
            p.setTiempoFinAb(finEjecucion);
            // Primero soluciono y entiendo todo lo de arriba antes de ponerme a calcular todas las metricas

            // Esta variable representa en donde inicia un proceso despues de la finalización de otro
            tiempoActual = finEjecucion;
        }
        
    }
    
}
