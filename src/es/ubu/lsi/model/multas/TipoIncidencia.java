package es.ubu.lsi.model.multas;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad TipoIncidencia.
 */
@Entity
@Table(name = "TIPOINCIDENCIA")
public class TipoIncidencia {

    @Id
    @Column(name = "id")
    private Long id;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "valor")
    private Integer valor;
    
    @OneToMany(mappedBy = "tipoIncidencia", fetch = FetchType.LAZY)
    private Set<Incidencia> incidencias = new HashSet<>();
    
    // Constructor vacío
    public TipoIncidencia() {
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Set<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(Set<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }
    
    @Override
    public String toString() {
        return "TipoIncidencia [id=" + id + ", descripcion=" + descripcion + ", valor=" + valor + "]";
    }
}