package es.ubu.lsi.model.multas;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clave primaria compuesta para la entidad Incidencia.
 */
@Embeddable
public class IncidenciaPK implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @Column(name = "nif")
    private String nif;
    
    // Constructor vacío requerido por JPA
    public IncidenciaPK() {
    }
    
    // Constructor con parámetros
    public IncidenciaPK(Date fecha, String nif) {
        this.fecha = fecha;
        this.nif = nif;
    }
    
    // Getters y Setters
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getNif() {
        return nif;
    }
    
    public void setNif(String nif) {
        this.nif = nif;
    }
    
    // Equals y HashCode (necesarios para claves compuestas)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncidenciaPK that = (IncidenciaPK) o;
        return Objects.equals(fecha, that.fecha) &&
               Objects.equals(nif, that.nif);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(fecha, nif);
    }
    
    @Override
    public String toString() {
        return "IncidenciaPK [fecha=" + fecha + ", nif=" + nif + "]";
    }
}