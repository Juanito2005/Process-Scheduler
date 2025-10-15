package dev.juanito;

public class Proceso {
    private Integer id;
    private Integer tiempoLlegada;
    private Integer duracionCPU;
    private Integer prioridad;
    // Atributos de salida
    private Integer tiempoInicioAb;
    private Integer tiempoFinAb;
    private Integer tiempoEspera;
    private Integer tiempoRetorno;
    private Integer tiempoRespuesta;

    public Proceso(Integer id, Integer tiempoLlegada, Integer duracionCPU, Integer prioridad) {
        this.id = id;
        this.tiempoLlegada = tiempoLlegada;
        this.duracionCPU = duracionCPU;
        this.prioridad = prioridad;
    }

    // Constructor para hacer una deepCopy de esta clase
     /*
        Para que esto funcione debe cumplir estas condiciones:
        1. Existir un constructor base con los atributos que quieres clonar
        2. En la clase que la vas a clonar, hacer un bucle for que asigne cada
        atributo de la instancia original a la clase clon
     */
    
    public Proceso(Proceso otro) {
        this.id = otro.id;
        this.tiempoLlegada = otro.tiempoLlegada;
        this.duracionCPU = otro.duracionCPU;
        this.prioridad = otro.prioridad;
    }

    public Proceso(Integer tiempoInicioAb, Integer tiempoFinAb, Integer tiempoEspera, Integer tiempoRetorno,
            Integer tiempoRespuesta) {
        this.tiempoInicioAb = tiempoInicioAb;
        this.tiempoFinAb = tiempoFinAb;
        this.tiempoEspera = tiempoEspera;
        this.tiempoRetorno = tiempoRetorno;
        this.tiempoRespuesta = tiempoRespuesta;
    }

    public Proceso(Integer id, Integer tiempoLlegada, Integer duracionCPU, Integer prioridad, Integer tiempoInicioAb,
            Integer tiempoFinAb, Integer tiempoEspera, Integer tiempoRetorno, Integer tiempoRespuesta) {
        this.id = id;
        this.tiempoLlegada = tiempoLlegada;
        this.duracionCPU = duracionCPU;
        this.prioridad = prioridad;
        this.tiempoInicioAb = tiempoInicioAb;
        this.tiempoFinAb = tiempoFinAb;
        this.tiempoEspera = tiempoEspera;
        this.tiempoRetorno = tiempoRetorno;
        this.tiempoRespuesta = tiempoRespuesta;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTiempoLlegada() {
        return tiempoLlegada;
    }
    public void setTiempoLlegada(Integer tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }
    public Integer getDuracionCPU() {
        return duracionCPU;
    }
    public void setDuracionCPU(Integer duracionCPU) {
        this.duracionCPU = duracionCPU;
    }
    public Integer getPrioridad() {
        return prioridad;
    }
    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }
    public Integer getTiempoInicioAb() {
        return tiempoInicioAb;
    }
    public void setTiempoInicioAb(Integer tiempoInicio) {
        this.tiempoInicioAb = tiempoInicio;
    }
    public Integer getTiempoFinAb() {
        return tiempoFinAb;
    }
    public void setTiempoFinAb(Integer tiempoFin) {
        this.tiempoFinAb = tiempoFin;
    }
    public Integer getTiempoEspera() {
        return tiempoEspera;
    }
    public void setTiempoEspera(Integer tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }
    public Integer getTiempoRetorno() {
        return tiempoRetorno;
    }
    public void setTiempoRetorno(Integer tiempoRetorno) {
        this.tiempoRetorno = tiempoRetorno;
    }
    public Integer getTiempoRespuesta() {
        return tiempoRespuesta;
    }
    public void setTiempoRespuesta(Integer tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }
}
