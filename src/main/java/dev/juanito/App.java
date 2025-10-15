package dev.juanito;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Proceso> procesos = new ArrayList<>();
        Integer roundRobinQuantum = null;
        String algoritmoElegido = "";

        System.out.println("=================================================");
        System.out.println("  SIMULADOR DE PLANIFICACIÓN DE PROCESOS");
        System.out.println("=================================================");

        // Menú de entrada de datos
        System.out.println("\n¿Cómo desea introducir los procesos?");
        System.out.println("1. Manualmente (por consola)");
        System.out.println("2. Generación aleatoria (mínimo 10 procesos)");
        System.out.print("Seleccione una opción: ");

        int opcionEntrada = getValidInteger(scanner, "Opción no válida. Por favor, ingrese 1 o 2: ", 1, 2);

        if (opcionEntrada == 1) {
            procesos = leerProcesosManualmente(scanner);
        } else {
            procesos = generarProcesosAleatorios();
        }

        // Menú de selección de algoritmo
        System.out.println("\nSeleccione el algoritmo de planificación a simular:");
        System.out.println("1. FIFO");
        System.out.println("2. Round Robin");
        System.out.println("3. SJF");
        System.out.println("4. Prioridades");
        System.out.print("Seleccione una opción: ");
        int opcionAlgoritmo = getValidInteger(scanner, "Opción no válida. Por favor, ingrese un número del 1 al 4: ", 1, 4);

        AlgoritmoPlanificacion planificador = null;

        switch (opcionAlgoritmo) {
            case 1:
                planificador = new PlanificadorFIFO();
                algoritmoElegido = "FIFO";
                break;
            case 2:
                planificador = new PlanificadorRoundRobin();
                algoritmoElegido = "Round Robin";
                System.out.print("Ingrese el valor del quantum (Q): ");
                roundRobinQuantum = getValidInteger(scanner, "Valor no válido. Ingrese un entero positivo: ", 1, Integer.MAX_VALUE);
                break;
            case 3:
                planificador = new PlanificadorSJF();
                algoritmoElegido = "SJF";
                break;
            case 4:
                planificador = new PlanificadorPrioridades();
                algoritmoElegido = "Prioridades";
                break;
        }

        // Ejecución de la simulación
        if (planificador != null) {
            System.out.println("\nIniciando simulación con el algoritmo " + algoritmoElegido + "...");
            ResultadoPlanificacion resultado = planificador.planificar(procesos, roundRobinQuantum);
            ImpresorConsola.imprimirReporte(algoritmoElegido, resultado, roundRobinQuantum);
        }

        scanner.close();
    }

    /**
     * Lee la entrada de procesos del usuario.
     */
    private static List<Proceso> leerProcesosManualmente(Scanner scanner) {
        List<Proceso> procesos = new ArrayList<>();
        System.out.print("Ingrese la cantidad de procesos a simular: ");
        int cantidadProcesos = getValidInteger(scanner, "Cantidad no válida. Ingrese un entero positivo: ", 1, Integer.MAX_VALUE);
        System.out.println("-------------------------------------------------");
        
        for (int i = 0; i < cantidadProcesos; i++) {
            System.out.println("Proceso " + (i + 1));
            System.out.print("  ID: ");
            int id = getValidInteger(scanner, "ID no válido. Ingrese un entero: ", 0, Integer.MAX_VALUE);
            System.out.print("  Tiempo de llegada: ");
            int llegada = getValidInteger(scanner, "Tiempo de llegada no válido. Ingrese un entero positivo o cero: ", 0, Integer.MAX_VALUE);
            System.out.print("  Duración de CPU: ");
            int duracion = getValidInteger(scanner, "Duración no válida. Ingrese un entero positivo: ", 1, Integer.MAX_VALUE);
            System.out.print("  Prioridad: ");
            int prioridad = getValidInteger(scanner, "Prioridad no válida. Ingrese un entero: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
            
            procesos.add(new Proceso(id, llegada, duracion, prioridad));
            System.out.println("-------------------------------------------------");
        }
        return procesos;
    }
    
    /**
     * Genera procesos aleatorios.
     */
    private static List<Proceso> generarProcesosAleatorios() {
        List<Proceso> procesos = new ArrayList<>();
        int cantidad = 10 + (int) (Math.random() * 10); // Entre 10 y 19 procesos
        System.out.println("\nGenerando " + cantidad + " procesos aleatorios...");
        for (int i = 0; i < cantidad; i++) {
            int id = i + 1;
            int llegada = (int) (Math.random() * 20); // Tiempo de llegada entre 0 y 19
            int duracion = 1 + (int) (Math.random() * 15); // Duración entre 1 y 15
            int prioridad = 1 + (int) (Math.random() * 10); // Prioridad entre 1 y 10
            procesos.add(new Proceso(id, llegada, duracion, prioridad));
        }
        return procesos;
    }

    /**
     * Método auxiliar para validar la entrada de enteros.
     */
    private static int getValidInteger(Scanner scanner, String mensajeError, int min, int max) {
        int valor = -1;
        while (true) {
            try {
                valor = scanner.nextInt();
                if (valor >= min && valor <= max) {
                    break;
                } else {
                    System.out.print(mensajeError);
                }
            } catch (InputMismatchException e) {
                System.out.print(mensajeError);
                scanner.next(); // Limpia el buffer de entrada
            }
        }
        return valor;
    }
}

// RoundRobin
// --- 3. Estadísticas Generales ---
// Tiempo Medio de Retorno: 18.00 ms
// Tiempo Medio de Espera:   11.80 ms
// Tiempo Medio de Respuesta: 4.60 ms
// Tiempo de Ejecución Total: 31 ms
// =======================================================

// Prioridades
// --- 3. Estadísticas Generales ---
// Tiempo Medio de Retorno: 14.00 ms
// Tiempo Medio de Espera:   7.80 ms
// Tiempo Medio de Respuesta: 7.80 ms
// Tiempo de Ejecución Total: 31 ms
// =======================================================

// FIFO
// --- 3. Estadísticas Generales ---
// Tiempo Medio de Retorno: 14.40 ms
// Tiempo Medio de Espera:   8.20 ms
// Tiempo Medio de Respuesta: 8.20 ms
// Tiempo de Ejecución Total: 31 ms
// =======================================================

// SJF
// --- 3. Estadísticas Generales ---
// Tiempo Medio de Retorno: 13.60 ms
// Tiempo Medio de Espera:   7.40 ms
// Tiempo Medio de Respuesta: 7.40 ms
// Tiempo de Ejecución Total: 31 ms
// =======================================================

// -------------------------------------------------
// Proceso 1
//   ID: 1
//   Tiempo de llegada: 0
//   Duración de CPU: 2
//   Prioridad: 4
// -------------------------------------------------
// Proceso 2
//   ID: 2
//   Tiempo de llegada: 4
//   Duración de CPU: 5
//   Prioridad: 2
// -------------------------------------------------
// Proceso 3
//   ID: 3
//   Tiempo de llegada: 3
//   Duración de CPU: 8
//   Prioridad: 5
// -------------------------------------------------
// Proceso 4
//   ID: 4
//   Tiempo de llegada: 1
//   Duración de CPU: 9
//   Prioridad: 3
// -------------------------------------------------
// Proceso 5
//   ID: 5
//   Tiempo de llegada: 7
//   Duración de CPU: 7
//   Prioridad: 1
// -------------------------------------------------