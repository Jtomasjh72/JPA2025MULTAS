package es.ubu.lsi.dao.multas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.multas.Incidencia;
import es.ubu.lsi.model.multas.IncidenciaPK;

/**
* DAO para la entidad Incidencia.
*/
public class IncidenciaDAO extends JpaDAO<Incidencia, IncidenciaPK> {

/**
* Constructor.
*
* @param em EntityManager
*/
public IncidenciaDAO(EntityManager em) {
super(em);
}

@Override
public List<Incidencia> findAll() {
TypedQuery<Incidencia> query = entityManager.createQuery(
"SELECT i FROM Incidencia i", Incidencia.class);
return query.getResultList();
}

/**
* Elimina todas las incidencias de un conductor.
*
* @param nif NIF del conductor
* @return Número de incidencias eliminadas
*/
public int deleteByNif(String nif) {
Query query = entityManager.createQuery(
"DELETE FROM Incidencia i WHERE i.conductor.nif = :nif");
query.setParameter("nif", nif);
return query.executeUpdate();
}

/**
* Busca todas las incidencias de un conductor.
*
* @param nif NIF del conductor
* @return Lista de incidencias del conductor
*/
public List<Incidencia> findByNif(String nif) {
TypedQuery<Incidencia> query = entityManager.createQuery(
"SELECT i FROM Incidencia i WHERE i.conductor.nif = :nif", Incidencia.class);
query.setParameter("nif", nif);
return query.getResultList();
}

/**
 * Actualiza una incidencia.
 * 
 * @param incidencia Incidencia a actualizar
 */
public void update(Incidencia incidencia) {
    entityManager.merge(incidencia);
}
}