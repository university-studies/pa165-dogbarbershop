/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.idao.ServiceDAO;
import fi.muni.pa165.entity.Service;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.joda.time.Duration;

/**
 * @author Honza
 */
public class ServiceDaoImpl implements ServiceDAO {

  @PersistenceContext(unitName = "PO")
  // FIXME EntityManager em1 = emf.createEntityManager();
  EntityManager em;

  ServiceDaoImpl(EntityManager em) {
    this.em = em;
  }

  @Override
  public Service getServiceById(Long id) {
    TypedQuery<Service> q = em.createQuery(
            "SELECT s FROM Service AS s WHERE s.id = :id", Service.class);
    q.setParameter("id", id);
    return q.getSingleResult();  // throws excptn if 0 || >1 results
  }

  @Override
  public List<Service> getServiceByName(String name) {
    TypedQuery<Service> q = em.createQuery(
            "SELECT s FROM Service AS s where s.name = :name", Service.class);
    q.setParameter("name", name);
    return q.getResultList();
  }

  @Override
  public List<Service> getServiceByPrice(Long price) {
    TypedQuery<Service> q = em.createQuery(
            "SELECT s FROM Service AS s where s.price = :price", Service.class);
    q.setParameter("price", price);
    return q.getResultList();
  }

  @Override
  public List<Service> getServiceByDuration(Duration duration) {
    TypedQuery<Service> q = em.createQuery(
            "SELECT s FROM Service AS s where s.duration = :duration", Service.class);
    q.setParameter("duration", duration);
    return q.getResultList();
  }
}