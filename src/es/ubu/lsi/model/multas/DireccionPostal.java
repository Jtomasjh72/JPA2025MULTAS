package es.ubu.lsi.model.multas;

import javax.persistence.Column;
import javax.persistence.Embeddable;



/**
 * Clase embebida para representar una dirección postal.
 * Se utiliza en Vehiculo y Conductor.
 */
@Embeddable
public class DireccionPostal {
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "cp")
    private String codigoPostal;
    
    @Column(name = "ciudad")
    private String ciudad;
    
    // Constructor vacío requerido por JPA
    public DireccionPostal() {
    }
    
    // Constructor con parámetros
    public DireccionPostal(String direccion, String codigoPostal, String ciudad) {
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
    }
    
    // Getters y Setters
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getCodigoPostal() {
        return codigoPostal;
    }
    
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
    public String getCiudad() {
        return ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    @Override
    public String toString() {
        return "DireccionPostal [direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", ciudad=" + ciudad + "]";
    }
}