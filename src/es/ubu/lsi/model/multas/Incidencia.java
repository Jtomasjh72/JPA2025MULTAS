package es.ubu.lsi.model.multas;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * Entidad Incidencia.
 */
@Entity
@Table(name = "INCIDENCIA")
public class Incidencia {

    @EmbeddedId
    private IncidenciaPK id;
    
    @Column(name = "anotacion")
    private String anotacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nif", insertable = false, updatable = false)
    @MapsId("nif")
    private Conductor conductor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtipo")
    private TipoIncidencia tipoIncidencia;
    
    // Constructor vacío
    public Incidencia() {
    }
    
    // Getters y Setters
    public IncidenciaPK getId() {
        return id;
    }

    public void setId(IncidenciaPK id) {
        this.id = id;
    }

    public String getAnotacion() {
        return anotacion;
    }

    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public TipoIncidencia getTipoIncidencia() {
        return tipoIncidencia;
    }

    public void setTipoIncidencia(TipoIncidencia tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }
    
    @Override
    public String toString() {
        return "Incidencia [id=" + id + ", anotacion=" + anotacion + ", conductor=" + conductor
                + ", tipoIncidencia=" + tipoIncidencia + "]";
    }
}