package dev.juanito;

public class Ejecucion {
    private Integer procesoId;
    private Integer momentoInicio;
    private Integer momentoFin;

    public Ejecucion(Integer procesoId, Integer momentoInicio, Integer momentoFin) {
        this.procesoId = procesoId;
        this.momentoInicio = momentoInicio;
        this.momentoFin = momentoFin;
    }
    public Integer getProcesoId() {
        return procesoId;
    }
    public void setProcesoId(Integer procesoId) {
        this.procesoId = procesoId;
    }
    public Integer getMomentoInicio() {
        return momentoInicio;
    }
    public void setMomentoInicio(Integer momentoInicio) {
        this.momentoInicio = momentoInicio;
    }
    public Integer getMomentoFin() {
        return momentoFin;
    }
    public void setMomentoFin(Integer momentoFin) {
        this.momentoFin = momentoFin;
    }
    
}
