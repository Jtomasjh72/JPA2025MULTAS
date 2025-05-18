package es.ubu.lsi.model.multas;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad Conductor.
 */
@Entity
@Table(name = "CONDUCTOR")
public class Conductor {

    @Id
    @Column(name = "nif")
    private String nif;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "apellido")
    private String apellido;
    
    @Embedded
    private DireccionPostal direccionPostal;
    
    @Column(name = "puntos")
    private Integer puntos;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idauto")
    private Vehiculo vehiculo;
    
    @OneToMany(mappedBy = "conductor", fetch = FetchType.LAZY)
    private Set<Incidencia> incidencias = new HashSet<>();
    
    // Constructor vacío
    public Conductor() {
    }
    
    // Getters y Setters
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public DireccionPostal getDireccionPostal() {
        return direccionPostal;
    }

    public void setDireccionPostal(DireccionPostal direccionPostal) {
        this.direccionPostal = direccionPostal;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Set<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(Set<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }
    
    // Método helper para añadir incidencia
    public void addIncidencia(Incidencia incidencia) {
        incidencias.add(incidencia);
        incidencia.setConductor(this);
    }
    
    @Override
    public String toString() {
        return "Conductor [nif=" + nif + ", nombre=" + nombre + ", apellido=" + apellido 
                + ", direccionPostal=" + direccionPostal + ", puntos=" + puntos + "]";
    }
}