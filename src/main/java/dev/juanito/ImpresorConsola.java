package dev.juanito;

import java.util.List;

public class ImpresorConsola {

    /**
     * Genera y muestra el reporte completo de la simulación en la consola.
     * @param algoritmo El nombre del algoritmo ejecutado.
     * @param resultado El objeto ResultadoPlanificacion que contiene los datos.
     * @param roundrobin El valor del quantum (si aplica).
     */
    public static void imprimirReporte(String algoritmo, ResultadoPlanificacion resultado, Integer roundrobin) {
        System.out.println("\n=======================================================");
        System.out.println("     REPORTE DE SIMULACIÓN - ALGORITMO: " + algoritmo.toUpperCase());
        if (roundrobin != null && roundrobin > 0) {
            System.out.println("     Quantum (Q): " + roundrobin + " ms");
        }
        System.out.println("=======================================================");

        imprimirTablaMetricas(resultado.getListaProcesos());
        imprimirDiagramaGantt(resultado.getEjecucion());
        imprimirEstadisticas(resultado);

        System.out.println("=======================================================\n");
    }

    /**
     * Muestra una tabla detallada con las métricas por cada proceso.
     */
    private static void imprimirTablaMetricas(List<Proceso> listaProcesos) {
        System.out.println("\n--- 1. Tabla de Métricas por Proceso ---");
        
        // Encabezados de la tabla
        String header = String.format("| %-4s | %-6s | %-6s | %-6s | %-6s | %-7s | %-6s | %-6s |", 
                                      "ID", "Lleg.", "Ráfaga", "Prio.", "Inicio", "Fin", "Retorno", "Espera", "Resp.");
        System.out.println("+------+--------+--------+--------+--------+---------+---------+---------+");
        System.out.println(header);
        System.out.println("+------+--------+--------+--------+--------+---------+---------+---------+");

        // Filas de datos
        for (Proceso p : listaProcesos) {
            String row = String.format("| P%-3d | %-6d | %-6d | %-6d | %-6d | %-7d | %-6d | %-6d | %-6d |",
                                       p.getId(), 
                                       p.getTiempoLlegada(), 
                                       p.getDuracionCPU_original() != null ? p.getDuracionCPU_original() : p.getDuracionCPU(),
                                       p.getPrioridad(),
                                       p.getTiempoInicioAb(),
                                       p.getTiempoFinAb(),
                                       p.getTiempoRetorno(),
                                       p.getTiempoEspera(),
                                       p.getTiempoRespuesta());
            System.out.println(row);
        }
        System.out.println("+------+--------+--------+--------+--------+---------+---------+---------+");
    }

    /**
     * Muestra un diagrama de Gantt textual basado en la auditoría de ejecución.
     */
    private static void imprimirDiagramaGantt(List<Ejecucion> ejecucion) {
        System.out.println("\n--- 2. Cronograma de Ejecución (Diagrama de Gantt) ---");
        
        StringBuilder ganttBar = new StringBuilder(); // La barra de IDs
        StringBuilder ganttTime = new StringBuilder(); // La línea de tiempo
        
        // Rellenar espacios muertos (IDLE) al inicio si el primer proceso no inicia en t=0
        if (ejecucion.get(0).getMomentoInicio() > 0) {
            int idleTime = ejecucion.get(0).getMomentoInicio();
            ganttBar.append("| IDLE ");
            ganttTime.append("0     ");
            for (int i = 0; i < idleTime - 1; i++) {
                ganttBar.append(" ");
            }
        }
        
        // Rellenar la ejecución de procesos
        for (Ejecucion e : ejecucion) {
            int duration = e.getMomentoFin() - e.getMomentoInicio();
            String idStr = "P" + e.getProcesoId();
            
            // Dibujar la barra de ID
            ganttBar.append("|");
            ganttBar.append(idStr);
            // Asegura que la barra tenga el ancho correcto (duración)
            for (int i = 0; i < duration - idStr.length(); i++) {
                ganttBar.append("-");
            }
            
            // Dibujar la marca de tiempo (solo en el final de cada bloque)
            ganttTime.append(String.format("%" + (duration) + "s", "")); // Espacio
            ganttTime.append(e.getMomentoFin());
        }
        ganttBar.append("|"); // Cerrar la última barra

        System.out.println(ganttBar.toString());
        System.out.println(ganttTime.toString());
    }

    /**
     * Muestra las estadísticas de promedio.
     */
    private static void imprimirEstadisticas(ResultadoPlanificacion resultado) {
        System.out.println("\n--- 3. Estadísticas Generales ---");
        System.out.printf("Tiempo Medio de Retorno: %.2f ms\n", resultado.getTiempoMedioRetorno());
        System.out.printf("Tiempo Medio de Espera:   %.2f ms\n", resultado.getTiempoMedioEspera());
        System.out.printf("Tiempo Medio de Respuesta: %.2f ms\n", resultado.getTiempoMediaRespuesta());
        
        // El tiempo de ejecución total es el momento en que termina el último proceso.
        if (!resultado.getEjecucion().isEmpty()) {
            int tiempoTotal = resultado.getEjecucion().get(resultado.getEjecucion().size() - 1).getMomentoFin();
            System.out.printf("Tiempo de Ejecución Total: %d ms\n", tiempoTotal);
        }
    }
}