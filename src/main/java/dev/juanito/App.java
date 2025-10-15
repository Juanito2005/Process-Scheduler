package dev.juanito;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // Instancia de la clase reader.entry para captar respuestas de usuario
    
        // Lógica de switch para que el usuario escoja un algoritmo de procesos
        
        // Se crea una instancia de la clase del algoritmo escogido

        // Se llama a los métodos base de este algoritmo

        // Los metodos devuelven un String que es presentado en pantalla de forma bonita aquí

        // Se centraliza la lógica de presentación por pantalla
    }

}

    // 1. Clonar y preparar colas
    // listaFinal           <- Lista vacía (Para los procesos terminados)
    // auditoriaList        <- Lista vacía (Para Ejecucion)
    
    // // Cola de procesos que aún no han llegado
    // colaLlegadas         <- deepCopy(procesosAPlanificar)
    // ordenar(colaLlegadas por tiempoLlegada ASC)

    // // Cola de procesos listos para la CPU (FIFO, porque RR es por orden de llegada)
    // colaListos           <- Queue (LinkedList o similar, FIFO)

    // tiempoActual         <- 0
    
    // // 2. Bucle Principal de Simulación
    // MIENTRAS colaLlegadas NO esté vacía O colaListos NO esté vacía:
        
    //     // A. Traer Procesos Recién Llegados
    //     // Mueve todos los procesos cuya llegada <= tiempoActual
    //     MIENTRAS colaLlegadas NO esté vacía Y colaLlegadas.cabeza.tiempoLlegada <= tiempoActual:
    //         colaListos.encolar(colaLlegadas.desencolar())
        
    //     // B. Manejar CPU Inactiva (IDLE)
    //     SI colaListos está vacía:
    //         // Si no hay nada que ejecutar, pero sí algo por llegar, avanza el tiempo
    //         SI colaLlegadas NO está vacía:
    //             tiempoActual = colaLlegadas.cabeza.tiempoLlegada
    //             CONTINUAR el bucle principal (vuelve arriba)
    //         SINO:
    //             // No hay nada que ejecutar ni nada por llegar (FIN)
    //             ROMPER bucle principal
        
    //     // C. Seleccionar Proceso (El primero en la cola FIFO)
    //     Proceso P_actual = colaListos.desencolar()
        
    //     // D. Determinar el Tiempo de Ejecución (Quantum o lo que le quede)
    //     // El tiempo de ejecución real es el mínimo entre el Quantum (Q) y la ráfaga_restante
    //     tiempoEjecutado = MIN(Q, P_actual.duracionCPU_restante)
        
    //     // E. Registrar y Actualizar
    //     inicioEjecucion = tiempoActual
    //     finEjecucion    = tiempoActual + tiempoEjecutado

    //     // Registrar Segmento de Ejecución (Auditoría)
    //     crear y añadir Ejecucion(P_actual.id, inicioEjecucion, finEjecucion) a auditoriaList

    //     // F. Actualizar Métricas (ANTES de avanzar el reloj)
        
    //     // Si es la primera vez que se ejecuta (solo para el Tiempo de Respuesta)
    //     SI P_actual.tiempoInicio == NULL:
    //         P_actual.tiempoInicio = inicioEjecucion
    //         P_actual.tiempoRespuesta = P_actual.tiempoInicio - P_actual.tiempoLlegada
            
    //     // G. Avanzar el Reloj y Determinar Estado Final
        
    //     // 1. Actualizar el tiempo restante y el reloj
    //     P_actual.duracionCPU_restante = P_actual.duracionCPU_restante - tiempoEjecutado
    //     tiempoActual = finEjecucion // AVANCE DISCRETO: El reloj salta
        
    //     // 2. ¿Terminó el Proceso?
    //     SI P_actual.duracionCPU_restante == 0:
    //         // Proceso TERMINADO (FINAL)
    //         P_actual.tiempoFin = tiempoActual
    //         P_actual.tiempoRetorno = P_actual.tiempoFin - P_actual.tiempoLlegada
    //         P_actual.tiempoEspera = P_actual.tiempoRetorno - P_actual.duracionCPU_original
            
    //         listaFinal.añadir(P_actual)
    //     SINO:
    //         // Proceso INTERRUMPIDO (Vuelve a la cola de listos)
    //         // Es vital hacer un nuevo chequeo de llegadas (Punto A) después de este avance del tiempo
    //         colaListos.encolar(P_actual)
            
    // // 3. Compactación y Retorno
    // ResultadoPlanificacion resultado = new ResultadoPlanificacion()
    // resultado.setListaProcesos(listaFinal)
    // resultado.setEjecucion(auditoriaList)
    // resultado.calcularPromedios()
    
    // RETORNAR resultado