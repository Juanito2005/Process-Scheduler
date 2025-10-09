package dev.juanito;

public class Proceso {
    private Integer id;
    private Integer tiempoLlegada;
    private Integer duracionCPU;
    private Integer prioridad;
    // Atributos de salida
    private Integer tiempoInicio;
    private Integer tiempoFin;
    private Integer tiempoEspera;
    private Integer tiempoRetorno;
    private Integer tiempoRespuesta;

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
    public Integer getTiempoInicio() {
        return tiempoInicio;
    }
    public void setTiempoInicio(Integer tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }
    public Integer getTiempoFin() {
        return tiempoFin;
    }
    public void setTiempoFin(Integer tiempoFin) {
        this.tiempoFin = tiempoFin;
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
