package dev.juanito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        Queue<Proceso> colaListos = new LinkedList<>();
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
            Integer tiempoEjecutado = Math.min(roundrobin, pActual.getDuracionCPU_restante());

            Integer inicioEjecucion = tiempoActual;
            Integer finEjecucion = tiempoActual + tiempoEjecutado;

            Ejecucion auditoria = new Ejecucion(pActual.getId(), inicioEjecucion, finEjecucion);
            auditoriaList.add(auditoria);

            if (pActual.getTiempoInicioAb() == null) {
                pActual.setTiempoInicioAb(inicioEjecucion);
                pActual.setTiempoRespuesta(pActual.getTiempoInicioAb() - pActual.getTiempoLlegada());
            }

            pActual.setDuracionCPU_restante(pActual.getDuracionCPU_restante() - tiempoEjecutado);
            tiempoActual = finEjecucion;

            if (pActual.getDuracionCPU_restante() == 0) {
                pActual.setTiempoFinAb(tiempoActual);
                pActual.setTiempoRetorno(pActual.getTiempoFinAb() - pActual.getTiempoLlegada());
                pActual.setTiempoEspera(pActual.getTiempoRetorno() - pActual.getDuracionCPU_original());
                listaFinal.add(pActual);
            } else {
                colaListos.offer(pActual);
            }
        }

        ResultadoPlanificacion resultado = new ResultadoPlanificacion();
        resultado.setListaProcesos(listaFinal);
        resultado.setEjecucion(auditoriaList);
        resultado.calcularPromedios();
        return resultado;
    }
    
    
}