package es.ubu.lsi.dao.multas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.multas.TipoIncidencia;

/**
 * DAO para la entidad TipoIncidencia.
 */
public class TipoIncidenciaDAO extends JpaDAO<TipoIncidencia, Long> {

    /**
     * Constructor.
     * 
     * @param em EntityManager
     */
    public TipoIncidenciaDAO(EntityManager em) {
        super(em);
    }

    @Override
    public List<TipoIncidencia> findAll() {
        TypedQuery<TipoIncidencia> query = entityManager.createQuery(
                "SELECT t FROM TipoIncidencia t", TipoIncidencia.class);
        return query.getResultList();
    }
    
    /**
     * Busca un tipo de incidencia por su ID.
     * 
     * @param id ID del tipo de incidencia
     * @return El tipo de incidencia o null si no existe
     */
    public TipoIncidencia findById(Long id) {
        return entityManager.find(TipoIncidencia.class, id);
    }
}