package es.ubu.lsi.dao.multas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.multas.Conductor;

/**
 * DAO para la entidad Conductor.
 */
public class ConductorDAO extends JpaDAO<Conductor, String> {

    /**
     * Constructor.
     * 
     * @param em EntityManager
     */
    public ConductorDAO(EntityManager em) {
        super(em);
    }

    @Override
    public List<Conductor> findAll() {
        TypedQuery<Conductor> query = entityManager.createQuery(
                "SELECT c FROM Conductor c", Conductor.class);
        return query.getResultList();
    }
    
    /**
     * Busca un conductor por su NIF.
     * 
     * @param nif NIF del conductor
     * @return El conductor o null si no existe
     */
    public Conductor findByNif(String nif) {
        return entityManager.find(Conductor.class, nif);
    }
    
    /**
     * Cuenta el número total de incidencias.
     * 
     * @return Número de incidencias
     */
    public int countIncidencias() {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(i) FROM Incidencia i", Long.class);
        return query.getSingleResult().intValue();
    }
    
    /**
     * Actualiza un conductor.
     * 
     * @param conductor Conductor a actualizar
     */
    public void update(Conductor conductor) {
        entityManager.merge(conductor);
    }
}