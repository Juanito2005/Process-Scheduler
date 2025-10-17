# 💻 Process Scheduler Simulator (Java)

A console application developed in **Java** to simulate and analyze the performance of various Operating System **process scheduling algorithms**. This project demonstrates core concepts of concurrent computing, resource management, and comparative analysis of different scheduling strategies.

---

## ✨ Features

This simulator accurately models the execution flow of processes, including arrival times, CPU bursts, and preemptive interruptions.

| Feature | Description | Status |
| :--- | :--- | :--- |
| **Algorithms** | Implements and compares four common scheduling strategies: **FIFO**, **SJF**, **Priorities** (Non-Preemptive), and **Round Robin** (Preemptive). | ✅ Implemented |
| **Dynamic Input** | Allows the user to input processes manually or generate a large set of random processes for simulation. | ✅ Implemented |
| **Metrics Calculation** | Calculates and reports key performance indicators for each process and overall averages: **Turnaround Time** (Tiempo de Retorno), **Waiting Time** (Tiempo de Espera), and **Response Time** (Tiempo de Respuesta). | ✅ Implemented |
| **Visual Output** | Provides clear console reports including a **Métricas Table** and a **Textual Gantt Chart** to visualize the temporal evolution of processes. | ✅ Implemented |
| **Round Robin Config** | Allows configuration of the **Quantum (Q)** size at runtime. | ✅ Implemented |

---

## 🚀 How to Run the Project

This is a standard Java console application.

### Prerequisites

* Java Development Kit (**JDK 8+**)

### Steps

1.  **Clone the Repository:**
    ```bash
    git clone [YOUR_REPOSITORY_URL]
    cd process-scheduler-simulator
    ```

2.  **Compile and Run (via Command Line):**
    ```bash
    # Compile the source files (adjusting the package path if necessary)
    javac dev/juanito/*.java

    # Execute the Main class
    java dev.juanito.Main
    ```

3.  **Follow the Console Prompts:**
    The application will guide you through:
    * Selecting the input method (Manual or Random Generation).
    * Choosing the scheduling algorithm (FIFO, RR, SJF, Priorities).
    * Entering the Quantum size (if Round Robin is selected).

---

## 🏛️ Project Architecture

The project follows the **Strategy Pattern** to separate the core simulation logic from the scheduling algorithms, making it highly modular and scalable.

| Component | Responsibility | Pattern Role |
| :--- | :--- | :--- |
| `AlgoritmoPlanificacion` | Interface defining the common `planificar()` method. | **Strategy** |
| `Planificador...` Classes | Implementations of specific scheduling algorithms (e.g., `PlanificadorFIFO`). | **Concrete Strategy** |
| `Proceso` | Encapsulates process state (ID, arrival, burst, remaining burst, metrics). | Context/State |
| `ResultadoPlanificacion` | Stores and aggregates final output data and average metrics. | Output Model |
| `Main` | Handles user input and orchestrates the simulation execution. | Client/Orchestrator |
| `ImpresorConsola` | Utility class for formatted presentation of results. | View/Reporting |

---

## 📊 Key Algorithm Implementation Details

### Non-Preemptive Algorithms (FIFO, SJF, Priorities)
These algorithms use a time-tracking variable (`tiempoActual`) and a **two-queue system** to correctly respect arrival times:
* **Arrival Queue (`colaLlegadas`):** Ensures processes are only available after their arrival time.
* **Ready Queue (`colaListos`):** Uses a `PriorityQueue` (for SJF/Priorities) or simple `List` (for FIFO) to select the next process based on the required criteria.

### Preemptive Algorithm (Round Robin)
Round Robin employs a **FIFO Queue** (`LinkedList`) for ready processes and relies on the state variable `duracionCPURestante` within the `Proceso` class to manage interruptions and accurately track waiting time.

---

## 👨‍💻 Author

**Juanito** - [Your GitHub Profile Link]
* **Role:** Project Developer & Simulator Logic
