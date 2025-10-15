package dev.juanito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PlanificadorRoundRobin implements AlgoritmoPlanificacion{

    @Override
    public ResultadoPlanificacion planificar(List<Proceso> procesosAPlanificar, Integer roundrobin) {

        // Listas finales a pasar a ResultadoPlanificación
        List<Proceso> listaFinal = new ArrayList<>();
        List<Ejecucion> auditoriaList = new ArrayList<>();

        // Se clona la lista original
        List<Proceso> colaLlegadas = new ArrayList<>();
        for (Proceso p : procesosAPlanificar) {
            colaLlegadas.add(new Proceso(p));
        }
        // Y se ordena por llegada
        colaLlegadas.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));

        // Se crea la cola de listos en formato "queue"
        PriorityQueue<Proceso> colaListos = new PriorityQueue<>(Comparator.comparingInt(Proceso::getTiempoLlegada));

        Integer tiempoActual = 0;

        while (!colaLlegadas.isEmpty() || !colaListos.isEmpty()) {
            
            while (!colaLlegadas.isEmpty() && colaLlegadas.get(0).getTiempoLlegada() <= tiempoActual) {
                colaListos.offer(colaLlegadas.remove(0));
            }

            if (colaListos.isEmpty()) {
                if (!colaLlegadas.isEmpty()) {
                    tiempoActual = colaLlegadas.get(0).getTiempoLlegada();
                    continue;
                } else {
                    break;
                }
            }

            Proceso pActual = colaListos.poll();

            Integer quantum = 3;
            Integer tiempoEjecutado = Math.min(quantum, pActual.getDuracionCPU_restante());

            Integer inicioEjecucion = tiempoActual;
            Integer finEjecucion = tiempoActual + tiempoEjecutado;

            Ejecucion ejecucion = new Ejecucion(pActual.getId(), inicioEjecucion, finEjecucion);

            if (pActual.getTiempoInicioAb() == null) {
                
            }
            
        }

        throw new UnsupportedOperationException("Unimplemented method 'planificar'");
    }
    
    
}