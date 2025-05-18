package es.ubu.lsi.dao.multas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.multas.Vehiculo;

/**
 * DAO para la entidad Vehiculo.
 */
public class VehiculoDAO extends JpaDAO<Vehiculo, String> {

    /**
     * Constructor.
     * 
     * @param em EntityManager
     */
    public VehiculoDAO(EntityManager em) {
        super(em);
    }

    @Override
    public List<Vehiculo> findAll() {
        TypedQuery<Vehiculo> query = entityManager.createQuery(
                "SELECT v FROM Vehiculo v", Vehiculo.class);
        return query.getResultList();
    }
    
    /**
     * Consulta todos los vehículos incluyendo conductores e incidencias usando un grafo de entidades.
     * 
     * @return Lista de vehículos con toda la información relacionada
     */
    public List<Vehiculo> findAllWithGraph() {
        TypedQuery<Vehiculo> query = entityManager.createQuery(
                "SELECT DISTINCT v FROM Vehiculo v " +
                "LEFT JOIN FETCH v.conductores c " +
                "LEFT JOIN FETCH c.incidencias i " +
                "LEFT JOIN FETCH i.tipoIncidencia", Vehiculo.class);
        return query.getResultList();
    }
    
    /**
     * Actualiza un vehículo.
     * 
     * @param vehiculo Vehículo a actualizar
     */
    public void update(Vehiculo vehiculo) {
        entityManager.merge(vehiculo);
    }
}
