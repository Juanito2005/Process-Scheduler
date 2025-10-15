package dev.juanito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlanificadorFIFO implements AlgoritmoPlanificacion {

    @Override
    public ResultadoPlanificacion planificar(List<Proceso> procesosAPlanificar, Integer roundrobin) {
        // La idea es que la lista enviada por el usuario no se cambie, de modo que se pueda reutilizar para ser enviada a otros algorithms
        List<Proceso> deepCopy = new ArrayList<>();
        for (Proceso proceso : procesosAPlanificar) {
            deepCopy.add(new Proceso(proceso));
        }

        // El .sort..(comparingInt) ordena de menor a mayor por defecto
        deepCopy.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));
        
        /*tiempoActual es básicamente una tercera variable para que el sistema
         * no ejecute un proceso cuando otro no ha terminado. Por ejemplo:
         *
         * P1. 8=duración 0=tiempoLlegada
         * P2. 4=duración 2=tiempoLlegada
         *
         * Sin la variable tiempo actual, el sistema ejecutaría el P2
         * en el "quantum 2" aun cuando todavía le quedan 6 "quantums" para que
         * el P1 termine
         */

        Integer tiempoActual = 0;
        List<Ejecucion> auditoriaList = new ArrayList<Ejecucion>();

        // Este ciclo for representa la "ejecucion" de los procesos y su auditoria
        for (Proceso p : deepCopy) {
            // Se establece el inicio de ejecución de un proceso, aquí tiempoActual brilla
            Integer inicioEjecucion = Math.max(p.getTiempoLlegada(), tiempoActual);
            Integer finEjecucion = inicioEjecucion + p.getDuracionCPU();
            Ejecucion auditoria = new Ejecucion(p.getId(), inicioEjecucion, finEjecucion);
            auditoriaList.add(auditoria);

            // En este caso como no es apropiativo el algoritmo, los tiempos de incio y fin son absolutos
            p.setTiempoInicioAb(inicioEjecucion);
            p.setTiempoFinAb(finEjecucion);
            
            // Calcular Métricas
            p.setTiempoRetorno(p.getTiempoFinAb() - p.getTiempoLlegada());
            //Esto es correcto, pero por qué?
            p.setTiempoEspera(p.getTiempoRetorno() - p.getDuracionCPU());
            p.setTiempoRespuesta(p.getTiempoInicioAb() - p.getTiempoLlegada());

            // Aquí se hace el punto de referencia de donde termina y comienza el siguiente proceso
            tiempoActual = finEjecucion;
        }

        ResultadoPlanificacion resultado = new ResultadoPlanificacion();
        resultado.setListaProcesos(deepCopy);
        resultado.setEjecucion(auditoriaList);
        resultado.calcularPromedios();

        return resultado;
    }

}
