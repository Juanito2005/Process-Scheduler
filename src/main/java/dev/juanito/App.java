package dev.juanito;

import java.util.List;

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

//     // 1. Preparación de Datos (Fuera de la simulación de tiempo)
// INICIO planificar(List<Proceso> procesos):

//     // CLONAR la lista de entrada (para no modificar la lista original que pasó el Main)
//     List<Proceso> listaClonada = deepCopy(procesos);
    
//     // ORDENAR la lista clonada por tiempoLlegada (obligatorio para saber quién entra primero)
//     ordenar(listaClonada por tiempoLlegada);

//     // Inicializar el reloj (tiempoActual) y el registro del Gantt
//     Integer tiempoActual = 0;
//     List<Ejecucion> cronograma = new ArrayList<Ejecucion>();
    
//     // La cola FIFO ya se simula al iterar sobre la lista ORDENADA.

//     // 2. Simulación: Iterar sobre los procesos ya ordenados por FIFO
//     PARA CADA Proceso P en listaClonada:

//         // A. Calcular Tiempo de Inicio (el momento en que realmente empieza P)
//         Integer inicioEjecucion = max(P.tiempoLlegada, tiempoActual);
        
//         // B. Calcular Tiempo de Fin
//         Integer finEjecucion = inicioEjecucion + P.duracionCPU;

//         // C. Registrar el evento (Auditoría: Clase Ejecucion)
//         crear y añadir Ejecucion(P.id, inicioEjecucion, finEjecucion) a cronograma;

//         // D. Actualizar Métricas Finales (Proceso)
//         P.setTiempoInicio(inicioEjecucion);
//         P.setTiempoFin(finEjecucion);
//         P.calcularTodasLasMetricas(); // (Espera, Retorno, Respuesta)

//         // E. Avanzar el reloj
//         tiempoActual = finEjecucion; // El reloj salta al final de la ejecución de P
    
//     // 3. Compactación y Retorno
//     ResultadoPlanificacion resultado = new ResultadoPlanificacion();
//     resultado.setListaProcesos(listaClonada);
//     resultado.setEjecucion(cronograma);
//     resultado.calcularPromedios(); // Debe iterar sobre listaClonada para sacar las medias
    
//     RETORNAR resultado;
// FIN