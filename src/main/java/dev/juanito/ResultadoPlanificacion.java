package dev.juanito;

import java.util.List;

public class ResultadoPlanificacion {
    private List<Proceso> listaProcesos;
    private List<Ejecucion> ejecucion;
    private Double tiempoMedioEspera;
    private Double tiempoMedioRetorno;
    private Double tiempoMediaRespuesta;

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
    public Double getTiempoMedioEspera() {
        return tiempoMedioEspera;
    }
    public void setTiempoMedioEspera(Double tiempoMedioEspera) {
        this.tiempoMedioEspera = tiempoMedioEspera;
    }
    public Double getTiempoMedioRetorno() {
        return tiempoMedioRetorno;
    }
    public void setTiempoMedioRetorno(Double tiempoMedioRetorno) {
        this.tiempoMedioRetorno = tiempoMedioRetorno;
    }
    public Double getTiempoMediaRespuesta() {
        return tiempoMediaRespuesta;
    }
    public void setTiempoMediaRespuesta(Double tiempoMediaRespuesta) {
        this.tiempoMediaRespuesta = tiempoMediaRespuesta;
    }

    public void calcularPromedios() {
        Double promedioRetorno = 0.0;
        Double promedioEspera = 0.0;
        Double promedioRespuesta = 0.0;
        
        for (Proceso p : listaProcesos) {
            promedioRetorno += p.getTiempoRetorno();
            promedioEspera += p.getTiempoEspera();
            promedioRespuesta += p.getTiempoRespuesta();
        }

        int numeroProcesos = listaProcesos.size();

        setTiempoMedioRetorno(promedioRetorno / numeroProcesos);
        setTiempoMedioEspera(promedioEspera / numeroProcesos);
        setTiempoMediaRespuesta(promedioRespuesta / numeroProcesos);
    }
    
}
