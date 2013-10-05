/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.joda.time.Duration;

/**
 * @author Honza
 */
public class ServiceDAO implements IServiceDAO {

  @PersistenceContext(unitName = "PO")
  // FIXME EntityManager em1 = emf.createEntityManager();
  EntityManager em;

  @Override
  public Service getServiceById(Long id) {
    TypedQuery<Service> q = em.createQuery(
            "SELECT x FROM Service s where s.id = :id", Service.class);
    if (q.getResultList().isEmpty()) {
      //FIXME
      throw new UnsupportedOperationException("No items found.");
    }

    return q.getResultList().get(0);
  }

  @Override
  public Service getServiceByName(String name) {
    TypedQuery<Service> q = em.createQuery(
            "SELECT x FROM Service s where s.name = :name", Service.class);
    if (q.getResultList().isEmpty()) {
      //FIXME
      throw new UnsupportedOperationException("No items found.");
    }

    return q.getResultList().get(0);
  }

  @Override
  public Service getServiceByPrice(Long price) {
    TypedQuery<Service> q = em.createQuery(
            "SELECT x FROM Service s where s.price = :price", Service.class);
    if (q.getResultList().isEmpty()) {
      //FIXME
      throw new UnsupportedOperationException("No items found.");
    }

    return q.getResultList().get(0);
  }

  @Override
  public Service getServiceByDuration(Duration duration) {
    TypedQuery<Service> q = em.createQuery(
            "SELECT x FROM Service s where s.duration = :duration", Service.class);
    if (q.getResultList().isEmpty()) {
      //FIXME
      throw new UnsupportedOperationException("No items found.");
    }

    return q.getResultList().get(0);
  }
}