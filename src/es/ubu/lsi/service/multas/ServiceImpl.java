package es.ubu.lsi.service.multas;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import es.ubu.lsi.dao.multas.ConductorDAO;
import es.ubu.lsi.dao.multas.IncidenciaDAO;
import es.ubu.lsi.dao.multas.TipoIncidenciaDAO;
import es.ubu.lsi.dao.multas.VehiculoDAO;
import es.ubu.lsi.model.multas.Conductor;
import es.ubu.lsi.model.multas.Incidencia;
import es.ubu.lsi.model.multas.IncidenciaPK;
import es.ubu.lsi.model.multas.TipoIncidencia;
import es.ubu.lsi.model.multas.Vehiculo;
import es.ubu.lsi.service.PersistenceException;
import es.ubu.lsi.service.PersistenceService;

/**
 * Implementación del servicio para gestionar incidencias.
 */
public class ServiceImpl extends PersistenceService implements Service {

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertarIncidencia(Date fecha, String nif, long tipo) throws PersistenceException {
        EntityManager em = null;
        try {
            em = createSession();
            beginTransaction(em);
            
            // Creamos los DAOs necesarios
            ConductorDAO conductorDao = new ConductorDAO(em);
            TipoIncidenciaDAO tipoIncidenciaDao = new TipoIncidenciaDAO(em);
            IncidenciaDAO incidenciaDao = new IncidenciaDAO(em);
            
            // Buscamos el conductor
            Conductor conductor = conductorDao.findByNif(nif);
            if (conductor == null) {
                throw new IncidentException(IncidentError.NOT_EXIST_DRIVER);
            }
            
            // Buscamos el tipo de incidencia
            TipoIncidencia tipoIncidencia = tipoIncidenciaDao.findById(tipo);
            if (tipoIncidencia == null) {
                throw new IncidentException(IncidentError.NOT_EXIST_INCIDENT_TYPE);
            }
            
            // Comprobamos si el conductor tiene puntos suficientes
            if (conductor.getPuntos() < tipoIncidencia.getValor()) {
                throw new IncidentException(IncidentError.NOT_AVAILABLE_POINTS);
            }
            
            // Creamos la incidencia
            Incidencia incidencia = new Incidencia();
            IncidenciaPK pk = new IncidenciaPK(fecha, nif);
            incidencia.setId(pk);
            incidencia.setConductor(conductor);
            incidencia.setTipoIncidencia(tipoIncidencia);
            
            // Actualizamos los puntos del conductor
            conductor.setPuntos(conductor.getPuntos() - tipoIncidencia.getValor());
            
            // Persistimos la incidencia y actualizamos el conductor
            incidenciaDao.persist(incidencia);
            conductorDao.update(conductor);
            
            commitTransaction(em);
        } catch (PersistenceException e) {
            if (em != null) {
                rollbackTransaction(em);
            }
            throw e;
        } catch (Exception e) {
            if (em != null) {
                rollbackTransaction(em);
            }
            throw new PersistenceException(e);
        } finally {
            if (em != null) {
                close(em);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void indultar(String nif) throws PersistenceException {
        EntityManager em = null;
        try {
            em = createSession();
            beginTransaction(em);
            
            // Creamos los DAOs necesarios
            ConductorDAO conductorDao = new ConductorDAO(em);
            IncidenciaDAO incidenciaDao = new IncidenciaDAO(em);
            
            // Buscamos el conductor
            Conductor conductor = conductorDao.findByNif(nif);
            if (conductor == null) {
                throw new IncidentException(IncidentError.NOT_EXIST_DRIVER);
            }
            
            // Eliminamos todas las incidencias del conductor
            incidenciaDao.deleteByNif(nif);
            
            // Restauramos los puntos al máximo
            conductor.setPuntos(MAXIMO_PUNTOS);
            
            // Actualizamos el conductor
            conductorDao.update(conductor);
            
            commitTransaction(em);
        } catch (PersistenceException e) {
            if (em != null) {
                rollbackTransaction(em);
            }
            throw e;
        } catch (Exception e) {
            if (em != null) {
                rollbackTransaction(em);
            }
            throw new PersistenceException(e);
        } finally {
            if (em != null) {
                close(em);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vehiculo> consultarVehiculos() throws PersistenceException {
        EntityManager em = null;
        try {
            em = createSession();
            beginTransaction(em);
            
            // Creamos el DAO necesario
            VehiculoDAO vehiculoDao = new VehiculoDAO(em);
            
            // Consultamos todos los vehículos con su información relacionada
            List<Vehiculo> vehiculos = vehiculoDao.findAllWithGraph();
            
            commitTransaction(em);
            return vehiculos;
        } catch (Exception e) {
            if (em != null) {
                rollbackTransaction(em);
            }
            throw new PersistenceException(e);
        } finally {
            if (em != null) {
                close(em);
            }
        }
    }
}