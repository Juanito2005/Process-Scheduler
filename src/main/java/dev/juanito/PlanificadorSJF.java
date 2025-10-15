package dev.juanito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PlanificadorSJF implements AlgoritmoPlanificacion {

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

        // SE CREA UNA NUEVA LISTA QUE VA A REPRESENTAR LOS PROCESOS LISTOS EN ESPERA
        // Pero por qué de tipo "PriorityQueue<>" y no "List<>"?
        PriorityQueue<Proceso> colaListos = new PriorityQueue<>(Comparator.comparingInt(Proceso::getDuracionCPU));

        Integer tiempoActual = 0;

        /* Bucle principal: Aquí se va toda la lógica
         * Básicamente, se ejecuta siempre y cuando hayan llegado procesos
         * Y haya alguno en la lista de espera
        */
        while (!colaLlegadas.isEmpty() || !colaListos.isEmpty()) {
            
            // Segun bucle: Este va a mover los procesos de la cola de llegada a listos
            // siempre y cuando la cola de llegadas no este vacía claro
            while (!colaLlegadas.isEmpty() && colaLlegadas.get(0).getTiempoLlegada() <= tiempoActual) {
                // Y teorizo que esta la razon por la cual la lista de "Listos" es de tipo "PriorityQueue"
                colaListos.offer(colaLlegadas.remove(0));
            }

            // Avanza el reloj en caso de que no haya procesos listos
            if (colaListos.isEmpty()) {
                if (!colaLlegadas.isEmpty()) {
                    tiempoActual = colaLlegadas.get(0).getTiempoLlegada();
                    // Vuelve al inicio del while para añadir el proceso recién llegado ahora que se modificó el reloj
                    continue;
                }
            }

            // "Retrieves and removes the head of this queue, or returns null if this queue is empty"
            Proceso p = colaListos.poll();

            Integer inicioEjecucion = tiempoActual;
            Integer finEjecucion = inicioEjecucion + p.getDuracionCPU();

            Ejecucion auditoria = new Ejecucion(p.getId(), inicioEjecucion, finEjecucion);
            auditoriaList.add(auditoria);

            p.setTiempoInicioAb(inicioEjecucion);
            p.setTiempoFinAb(finEjecucion);
            p.setTiempoRetorno(p.getTiempoFinAb() - p.getTiempoLlegada());
            p.setTiempoEspera(p.getTiempoRetorno() - p.getDuracionCPU());
            p.setTiempoRespuesta(p.getTiempoInicioAb() - p.getTiempoLlegada());

            tiempoActual = finEjecucion;
            listaFinal.add(p);
        }

        ResultadoPlanificacion resultado = new ResultadoPlanificacion();
        resultado.setListaProcesos(listaFinal);
        resultado.setEjecucion(auditoriaList);
        resultado.calcularPromedios();
        return resultado;
    }
    
}
