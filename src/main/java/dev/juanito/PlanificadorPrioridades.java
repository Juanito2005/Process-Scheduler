package dev.juanito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PlanificadorPrioridades implements AlgoritmoPlanificacion{

    @Override
    public ResultadoPlanificacion planificar(List<Proceso> procesosAPlanificar, Integer roundrobin) {

        List<Proceso> listaFinal = new ArrayList<>();
        List<Ejecucion> auditoriaList = new ArrayList<>();
        
        // 1. Clonar y Ordenar por LLEGADA para SIMULAR EL FLUJO DE ENTRADA
        List<Proceso> colaLlegadas = new ArrayList<>();
        for (Proceso p : procesosAPlanificar) {
            colaLlegadas.add(new Proceso(p));
        }
        // Ordenamos por llegada (menor tiempo de llegada primero)
        colaLlegadas.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));

        // 2. Cola de Prioridades para la SELECCIÓN de la CPU
        // Se usa un Comparator para ordenar por PRIORIDAD (menor valor = mayor prioridad)
        PriorityQueue<Proceso> colaListos = new PriorityQueue<>(
            Comparator.comparingInt(Proceso::getPrioridad)
        );

        Integer tiempoActual = 0;

        // 3. Bucle Principal de la Simulación
        // Continúa mientras haya procesos en la cola de llegadas O en la cola de listos
        while (!colaLlegadas.isEmpty() || !colaListos.isEmpty()) {

            // A. Mover Procesos de Llegadas a Listos
            // Mueve todos los procesos que ya llegaron a la cola de prioridades
            // Se garantiza que no se ejecuten procesos mientras ya esta otro en ejecución
            while (!colaLlegadas.isEmpty() && colaLlegadas.get(0).getTiempoLlegada() <= tiempoActual) {
                colaListos.offer(colaLlegadas.remove(0));
            }

            // B. Gestión del Tiempo de Inactividad (CPU Idle)
            // Si la cola de listos está vacía, pero aún hay procesos por llegar,
            // avanza el reloj al tiempo de llegada del siguiente proceso
            if (colaListos.isEmpty()) {
                if (!colaLlegadas.isEmpty()) {
                    tiempoActual = colaLlegadas.get(0).getTiempoLlegada();
                    continue; // Vuelve al inicio del while para añadir el proceso recién llegado
                }
            }

            // C. Seleccionar y Ejecutar el Proceso de MAYOR PRIORIDAD
            Proceso p = colaListos.poll(); // Toma el de mayor prioridad

            // D. Ejecución y Actualización de Métricas (Igual que en FIFO)
            Integer inicioEjecucion = tiempoActual; // NO es necesario Math.max() aquí, ya que el tiempoActual garantiza el inicio.
            Integer finEjecucion = inicioEjecucion + p.getDuracionCPU();
            
            // Registro y Auditoría
            Ejecucion auditoria = new Ejecucion(p.getId(), inicioEjecucion, finEjecucion);
            auditoriaList.add(auditoria);

            // Actualizar Métricas Finales
            p.setTiempoInicioAb(inicioEjecucion);
            p.setTiempoFinAb(finEjecucion);
            p.setTiempoRetorno(p.getTiempoFinAb() - p.getTiempoLlegada());
            p.setTiempoEspera(p.getTiempoRetorno() - p.getDuracionCPU());
            p.setTiempoRespuesta(p.getTiempoInicioAb() - p.getTiempoLlegada());

            // E. AVANZAR EL RELOJ (Al final del proceso ejecutado)
            tiempoActual = finEjecucion;
            listaFinal.add(p); // Añadir a la lista de resultados
        }
       

        ResultadoPlanificacion resultado = new ResultadoPlanificacion();
        resultado.setListaProcesos(listaFinal);
        resultado.setEjecucion(auditoriaList);
        resultado.calcularPromedios();
        return resultado;
    }
    
}
