package es.ubu.lsi.model.multas;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad Vehiculo.
 */
@Entity
@Table(name = "VEHICULO")
public class Vehiculo {

    @Id
    @Column(name = "idauto")
    private String idauto;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Embedded
    private DireccionPostal direccionPostal;
    
    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private Set<Conductor> conductores = new HashSet<>();
    
    // Constructor vacío
    public Vehiculo() {
    }
    
    // Getters y Setters
    public String getIdauto() {
        return idauto;
    }

    public void setIdauto(String idauto) {
        this.idauto = idauto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DireccionPostal getDireccionPostal() {
        return direccionPostal;
    }

    public void setDireccionPostal(DireccionPostal direccionPostal) {
        this.direccionPostal = direccionPostal;
    }

    public Set<Conductor> getConductores() {
        return conductores;
    }

    public void setConductores(Set<Conductor> conductores) {
        this.conductores = conductores;
    }
    
    // Método helper para añadir conductor
    public void addConductor(Conductor conductor) {
        conductores.add(conductor);
        conductor.setVehiculo(this);
    }
    
    @Override
    public String toString() {
        return "Vehiculo [idauto=" + idauto + ", nombre=" + nombre + ", direccionPostal=" + direccionPostal + "]";
    }
}