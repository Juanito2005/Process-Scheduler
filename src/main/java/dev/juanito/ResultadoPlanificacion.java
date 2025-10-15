package dev.juanito;

import java.util.List;

public class ResultadoPlanificacion {
    private List<Proceso> listaProcesos;
    private List<Ejecucion> ejecucion;
    private Integer tiempoMedioEspera;
    private Integer tiempoMedioRetorno;
    private Integer tiempoMediaRespuesta;

    public List<Proceso> getListaProcesos() {
        return listaProcesos;
    }
    public void setListaProcesos(List<Proceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
    }
    public List<Ejecucion> getEjecucion() {
        return ejecucion;
    }
    public void setEjecucion(List<Ejecucion> ejecucion) {
        this.ejecucion = ejecucion;
    }
    public Integer getTiempoMedioEspera() {
        return tiempoMedioEspera;
    }
    public void setTiempoMedioEspera(Integer tiempoMedioEspera) {
        this.tiempoMedioEspera = tiempoMedioEspera;
    }
    public Integer getTiempoMedioRetorno() {
        return tiempoMedioRetorno;
    }
    public void setTiempoMedioRetorno(Integer tiempoMedioRetorno) {
        this.tiempoMedioRetorno = tiempoMedioRetorno;
    }
    public Integer getTiempoMediaRespuesta() {
        return tiempoMediaRespuesta;
    }
    public void setTiempoMediaRespuesta(Integer tiempoMediaRespuesta) {
        this.tiempoMediaRespuesta = tiempoMediaRespuesta;
    }
    
}
